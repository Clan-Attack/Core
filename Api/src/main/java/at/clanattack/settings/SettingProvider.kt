package at.clanattack.settings

import kotlin.reflect.KClass

object SettingProvider {

    var instance: ISettingServiceProvider? = null

    private val safeInstance: ISettingServiceProvider
        get() = instance ?: throw IllegalStateException("Settings not initialized yet")

    fun <T : Any> getSetting(key: String, clazz: KClass<T>) = this.safeInstance.getSetting(key, clazz)

    fun <T : Any> getSetting(key: String, default: T, clazz: KClass<T>) =
        this.safeInstance.getSetting(key, default, clazz)

    fun <T : Any> getSettingAsync(key: String, clazz: KClass<T>) = this.safeInstance.getSettingAsync(key, clazz)

    fun <T : Any> getSettingAsync(key: String, default: T, clazz: KClass<T>) =
        this.safeInstance.getSettingAsync(key, default, clazz)

}

fun <T : Any> getSetting(key: String, clazz: KClass<T>) = SettingProvider.getSetting(key, clazz)

fun <T : Any> getSetting(key: String, default: T, clazz: KClass<T>) = SettingProvider.getSetting(key, default, clazz)

fun <T : Any> getSettingAsync(key: String, clazz: KClass<T>) = SettingProvider.getSettingAsync(key, clazz)

fun <T : Any> getSettingAsync(key: String, default: T, clazz: KClass<T>) =
    SettingProvider.getSettingAsync(key, default, clazz)