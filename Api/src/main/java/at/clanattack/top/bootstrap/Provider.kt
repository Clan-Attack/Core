package at.clanattack.top.bootstrap

import at.clanattack.bootstrap.provider.IServiceProvider
import at.clanattack.top.TopCore
import kotlin.reflect.KClass

fun <T : IServiceProvider> getServiceProvider(`class`: Class<T>) = TopCore.core.getServiceProvider(`class`)

fun <T : IServiceProvider> getServiceProvider(`class`: KClass<T>) = TopCore.core.getServiceProvider(`class`)

inline fun <reified T : IServiceProvider> getServiceProvider(): T = TopCore.core.getServiceProvider(T::class)