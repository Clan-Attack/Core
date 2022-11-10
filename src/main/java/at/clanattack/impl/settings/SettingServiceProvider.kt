package at.clanattack.impl.settings

import at.clanattack.bootstrap.ICore
import at.clanattack.bootstrap.provider.AbstractServiceProvider
import at.clanattack.bootstrap.provider.ServiceProvider
import at.clanattack.xjkl.json.JsonDocument
import at.clanattack.database.ISurrealServiceProvider
import at.clanattack.impl.settings.model.Setting
import at.clanattack.settings.ISettingServiceProvider
import at.clanattack.xjkl.future.CompletableFuture
import at.clanattack.xjkl.future.Future
import at.clanattack.xjkl.future.ToUnitFuture
import at.clanattack.xjkl.scope.fromT
import at.clanattack.xjkl.scope.toT
import com.google.common.cache.CacheBuilder
import com.google.gson.JsonPrimitive
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

@ServiceProvider(ISettingServiceProvider::class, [ISurrealServiceProvider::class])
class SettingServiceProvider(core: ICore) : AbstractServiceProvider(core), ISettingServiceProvider {

    private val settingCache = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES)
        .build<String, String>()

    override fun setSettingAsync(key: String, value: Any): Future<Unit> {
        return ToUnitFuture(
            this.core.getServiceProvider(ISurrealServiceProvider::class)
                .update(
                    "setting:${key.replace(".", "_")}",
                    Setting(value.fromT())
                )
        )
    }

    override fun <T : Any> getSettingAsync(key: String, clazz: KClass<T>) =
        this.getSettingFromDb(key, null, clazz.java)

    override fun <T : Any> getSettingAsync(key: String, default: T, clazz: KClass<T>): Future<T> {
        val future = CompletableFuture<T>()
        this.getSettingFromDb(key, default, clazz.java).then { future.complete(it!!) }

        return future
    }

    private fun <T> getSettingFromDb(key: String, default: T?, clazz: Class<T>): Future<T?> {
        val future = CompletableFuture<T?>()
        val setting = settingCache.getIfPresent(key)
        if (setting != null) return future.complete(setting.toT(clazz))

        this.core.getServiceProvider(ISurrealServiceProvider::class)
            .select("setting:${key.replace(".", "_")}", Setting::class).then {
                if (it.isEmpty()) {
                    future.complete(default)
                    return@then
                }

                future.complete(it[0].setting.toT(clazz) ?: default)
            }

        return future
    }

}