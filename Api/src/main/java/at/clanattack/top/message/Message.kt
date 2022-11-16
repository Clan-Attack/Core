package at.clanattack.top.message

import at.clanattack.message.IMessageServiceProvider
import at.clanattack.top.bootstrap.getServiceProvider

private val provider: IMessageServiceProvider
    get() = getServiceProvider()

fun getMessage(key: String, vararg placeholders: String = emptyArray()) =
    provider.getMessage(key, *placeholders)

fun getMessageAsync(key: String, vararg placeholders: String = emptyArray()) =
    provider.getMessageAsync(key, *placeholders)

fun getStringMessage(key: String, vararg placeholders: String = emptyArray()) =
    provider.getStringMessage(key, *placeholders)

fun getStringMessageAsync(key: String, vararg placeholders: String = emptyArray()) =
    provider.getStringMessageAsync(key, *placeholders)

fun registerGlobalPlaceholder(key: String, replaceKey: String) = provider.registerGlobalPlaceholder(key, replaceKey)

fun replaceColors(message: String, code: Char) = provider.replaceColors(message, code)