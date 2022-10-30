package at.clanattack.bootstrap.util.log

interface ILogger {

    fun info(message: String)

    fun warn(message: String)

    fun error(message: String, throwable: Throwable? = null)

}