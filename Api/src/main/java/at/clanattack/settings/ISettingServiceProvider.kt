package at.clanattack.settings

import at.clanattack.bootstrap.provider.IServiceProvider
import at.clanattack.xjkl.future.Future
import kotlin.reflect.KClass

interface ISettingServiceProvider : IServiceProvider {

    fun setSetting(key: String, value: Any) = this.setSettingAsync(key, value).getSync()

    fun setSettingAsync(key: String, value: Any): Future<Unit>

    fun <T : Any> getSetting(key: String, clazz: KClass<T>) = this.getSettingAsync(key, clazz).getSync()

    fun <T : Any> getSetting(key: String, default: T, clazz: KClass<T>) =
        this.getSettingAsync(key, default, clazz).getSync()

    fun <T : Any> getSettingAsync(key: String, clazz: KClass<T>): Future<T?>

    fun <T : Any> getSettingAsync(key: String, default: T, clazz: KClass<T>): Future<T>

}

// Extension
inline fun <reified T : Any> ISettingServiceProvider.getSetting(key: String) = this.getSetting(key, T::class)

inline fun <reified T : Any> ISettingServiceProvider.getSettingAsync(key: String) = this.getSettingAsync(key, T::class)
