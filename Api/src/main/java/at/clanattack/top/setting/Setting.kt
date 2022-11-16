package at.clanattack.top.setting

import at.clanattack.settings.ISettingServiceProvider
import at.clanattack.settings.getSetting
import at.clanattack.settings.getSettingAsync
import at.clanattack.top.bootstrap.getServiceProvider
import kotlin.reflect.KClass

private val provider: ISettingServiceProvider
    get() = getServiceProvider()

fun setSetting(key: String, value: Any) = provider.setSetting(key, value)

fun setSettingAsync(key: String, value: Any) = provider.setSettingAsync(key, value)

fun <T : Any> getSetting(key: String, clazz: KClass<T>) = provider.getSetting(key, clazz)

fun <T : Any> getSetting(key: String, default: T, clazz: KClass<T>) =
    provider.getSettingAsync(key, default, clazz)

fun <T : Any> getSettingAsync(key: String, clazz: KClass<T>) = provider.getSettingAsync(key, clazz)

fun <T : Any> getSettingAsync(key: String, default: T, clazz: KClass<T>) = provider.getSettingAsync(key, default, clazz)

inline fun <reified T : Any> getSetting(key: String) = getServiceProvider<ISettingServiceProvider>().getSetting<T>(key)

inline fun <reified T : Any> getSettingAsync(key: String) =
    getServiceProvider<ISettingServiceProvider>().getSettingAsync<T>(key)