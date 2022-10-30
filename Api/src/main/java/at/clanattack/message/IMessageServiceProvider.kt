package at.clanattack.message

import at.clanattack.bootstrap.provider.IServiceProvider
import at.clanattack.xjkl.future.Future
import net.kyori.adventure.text.Component

interface IMessageServiceProvider: IServiceProvider {

    fun getMessage(key: String, vararg placeholders: String = emptyArray()) =
        this.getMessageAsync(key, *placeholders).getSync()

    fun getMessageAsync(key: String, vararg placeholders: String = emptyArray()): Future<Component>

    fun getStringMessage(key: String, vararg placeholders: String = emptyArray()) =
        this.getStringMessageAsync(key, *placeholders).getSync()

    fun getStringMessageAsync(key: String, vararg placeholders: String = emptyArray()): Future<String>

    fun registerGlobalPlaceholder(key: String, replaceKey: String)

    fun replaceColors(message: String, code: Char): Component

    operator fun get(key: String, vararg placeholders: String = emptyArray()) =
        getMessage(key, *placeholders)

}