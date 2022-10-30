package at.clanattack.impl.message

import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import at.clanattack.bootstrap.ICore
import at.clanattack.database.ISurrealServiceProvider
import at.clanattack.database.select
import at.clanattack.xjkl.future.CompletableFuture
import at.clanattack.xjkl.future.Future
import at.clanattack.xjkl.scope.asExpr
import at.clanattack.impl.message.model.Message
import java.text.MessageFormat
import java.util.concurrent.TimeUnit

class MessageService(private val core: ICore) {

    private val messageNotFound = "&cDiese Nachricht ({0}) konnte nicht gefunden werden."

    private val globalPlaceholders = mutableMapOf<String, String>()
    private val cache: Cache<String, String> = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES).build()

    fun getMessageAsync(key: String, placeholders: Array<out String>): Future<String> {
        val future = CompletableFuture<String>()

        this.getFromDatabase(key)
            .then { dbMessage ->
                if (dbMessage == null) {
                    future.complete(MessageFormat.format(messageNotFound, key))
                    return@then
                }

                var message: String = dbMessage

                placeholders.forEach {
                    val split = it.split("=>")
                    message = message.replace("%${split[0]}%", it.substring(split[0].length + 2))
                }

                var found = 0
                var run = 0
                globalPlaceholders.forEach { (k, v) ->
                    if (message.contains("%$k%")) {
                        found++

                        this.getFromDatabase(v)
                            .then global@{ result ->
                                run++

                                if (result == null) return@global
                                message = message.replace("%$k%", result)

                                if (found == run) future.complete(message)
                            }
                    }
                }

                if (found == 0) future.complete(message)

            }

        return future
    }

    fun registerGlobalPlaceholder(key: String, replaceKey: String) =
        asExpr { this.globalPlaceholders[key] = replaceKey }

    private fun getFromDatabase(key: String): Future<String?> {
        val future = CompletableFuture<String?>()
        val message = cache.getIfPresent(key)
        if (message != null) return future.complete(message)

        this.core.getServiceProvider(ISurrealServiceProvider::class)
            .select<Message>("message:${key.replace(".", "_")}")
            .then { result ->
                if (result.isEmpty()) {
                    future.complete(null)
                    return@then
                }

                cache.put(key, result[0].message)
                future.complete(result[0].message)
            }

        return future
    }

}