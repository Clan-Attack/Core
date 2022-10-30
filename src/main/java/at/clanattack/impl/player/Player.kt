package at.clanattack.impl.player

import at.clanattack.bootstrap.ICore
import com.google.gson.JsonPrimitive
import com.surrealdb.driver.model.patch.RemovePatch
import at.clanattack.bootstrap.util.json.JsonDocument
import at.clanattack.database.ISurrealServiceProvider
import at.clanattack.player.IPlayer
import at.clanattack.player.actionbar.ActionbarPriority
import at.clanattack.xjkl.extention.supplyNullable
import at.clanattack.xjkl.future.CompletableFuture
import at.clanattack.xjkl.future.Future
import at.clanattack.xjkl.future.ToUnitFuture
import at.clanattack.impl.player.model.DBPlayer
import at.clanattack.impl.player.model.PlayerDataUpdate
import at.clanattack.message.IMessageServiceProvider
import at.clanattack.utility.IUtilityServiceProvider
import org.bukkit.Bukkit
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

class Player(
    private val core: ICore,
    private val uuid: UUID
) : IPlayer {

    private val surreal: ISurrealServiceProvider
        get() = this.core.getServiceProvider(ISurrealServiceProvider::class)

    override val online: Boolean
        get() = Bukkit.getOfflinePlayer(this.uuid).isOnline

    override fun getOnlineTimeAsync(): Future<Long> {
        val future = CompletableFuture<Long>()

        this.surreal.select("player:`${this.uuid}`", DBPlayer::class).then {
            if (it.isEmpty()) {
                future.fail(IllegalStateException("Player was not created"))
                return@then
            }

            future.complete(
                it[0].onlineTime + if (this.online) (System.currentTimeMillis() - (joinTime[this.uuid]
                    ?: System.currentTimeMillis())) else 0L
            )
        }

        return future
    }

    override fun sendActionbar(priority: ActionbarPriority, stay: Int, key: String, vararg placeholders: String) {
        if (this.uuid in Actionbar && Actionbar[uuid]!!.priority.value > priority.value) return

        Actionbar[this.uuid] = ActionbarInformation(
            this.core.getServiceProvider(IMessageServiceProvider::class).getMessage(key, *placeholders),
            System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(stay.toLong()),
            priority
        )
    }

    override fun <T : Any> getPlayerDataAsync(key: String, clazz: KClass<T>): Future<T?> {
        val future = CompletableFuture<T?>()
        this.getPlayerDataAsync().then { data -> future.complete(data[key].supplyNullable { toT(it, clazz.java) }) }
        return future
    }

    override fun <T : Any> getPlayerDataAsync(key: String, default: T, clazz: KClass<T>): Future<T> {
        val future = CompletableFuture<T>()
        this.getPlayerDataAsync()
            .then { data -> future.complete(data[key].supplyNullable { toT(it, clazz.java) } ?: default) }
        return future
    }

    override fun getPlayerDataAsync(): Future<Map<String, String?>> {
        val future = CompletableFuture<Map<String, String?>>()

        this.surreal.select("player:`${this.uuid}`", DBPlayer::class).then {
            if (it.isEmpty()) {
                future.fail(IllegalStateException("Player was not created"))
                return@then
            }

            future.complete(it[0].playerData)
        }

        return future
    }

    override fun setPlayerDataAsync(key: String, value: Any): Future<Unit> {
        return ToUnitFuture(
            this.surreal.change(
                "player:`${this.uuid}`",
                PlayerDataUpdate.create(key, toString(value)),
                DBPlayer::class
            )
        )
    }

    override fun hasPlayerDataAsync(key: String): Future<Boolean> {
        val future = CompletableFuture<Boolean>()
        this.getPlayerDataAsync()
        return future
    }

    override fun removePlayerDataAsync(key: String): Future<Unit> {
        return this.surreal.patch("player:`${this.uuid}`", listOf(RemovePatch("playerData.$key")))
    }

    companion object {

        val joinTime = mutableMapOf<UUID, Long>()

        private fun <T> toT(value: String?, clazz: Class<T>): T? {
            if (value == null) return null

            val json = JsonPrimitive(value)
            return JsonDocument.gson.fromJson(json, clazz)
        }

        private fun toString(value: Any?): String? {
            if (value == null) return null

            return JsonDocument.gson.toJson(value)
        }

    }


}