package at.clanattack.settings

import at.clanattack.bootstrap.provider.IServiceProvider
import at.clanattack.xjkl.future.Future
import kotlin.reflect.KClass

interface ISettingServiceProvider : IServiceProvider {

    fun <T : Any> getSetting(key: String, clazz: KClass<T>) = this.getSettingAsync(key, clazz).getSync()

    fun <T : Any> getSetting(key: String, default: T, clazz: KClass<T>) = this.getSettingAsync(key, default, clazz).getSync()

    fun <T : Any> getSettingAsync(key: String, clazz: KClass<T>): Future<T?>

    fun <T : Any> getSettingAsync(key: String, default: T, clazz: KClass<T>): Future<T>

}