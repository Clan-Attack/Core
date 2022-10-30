package at.clanattack.message

object MessageProvider {

    var instance: IMessageServiceProvider? = null

    private val safeInstance: IMessageServiceProvider
        get() = instance ?: throw IllegalStateException("Messaging not initialized yet")

    fun getMessage(key: String, vararg placeholders: String = emptyArray()) =
        safeInstance.getMessage(key, *placeholders)

    fun getMessageAsync(key: String, vararg placeholders: String = emptyArray()) =
        safeInstance.getMessageAsync(key, *placeholders)

    fun getStringMessage(key: String, vararg placeholders: String = emptyArray()) =
        safeInstance.getStringMessage(key, *placeholders)

    fun getStringMessageAsync(key: String, vararg placeholders: String = emptyArray()) =
        safeInstance.getStringMessageAsync(key, *placeholders)

    fun registerGlobalPlaceholder(key: String, replaceKey: String) =
        safeInstance.registerGlobalPlaceholder(key, replaceKey)

    fun replaceColors(message: String, code: Char) = safeInstance.replaceColors(message, code)

}

fun getMessage(key: String, vararg placeholders: String = emptyArray()) = MessageProvider.getMessage(key, *placeholders)

fun getMessageAsync(key: String, vararg placeholders: String = emptyArray()) =
    MessageProvider.getMessageAsync(key, *placeholders)

fun getStringMessage(key: String, vararg placeholders: String = emptyArray()) =
    MessageProvider.getStringMessage(key, *placeholders)

fun getStringMessageAsync(key: String, vararg placeholders: String = emptyArray()) =
    MessageProvider.getStringMessageAsync(key, *placeholders)

fun registerGlobalPlaceholder(key: String, replaceKey: String) =
    MessageProvider.registerGlobalPlaceholder(key, replaceKey)

fun replaceColors(message: String, code: Char) = MessageProvider.replaceColors(message, code)
