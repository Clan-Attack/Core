package at.clanattack.impl.bootstrap.util.log

import at.clanattack.bootstrap.ICore
import at.clanattack.bootstrap.util.log.ILogger
import java.util.logging.Level

class Logger(core: ICore) : ILogger {

    private val logger = core.javaPlugin.logger

    override fun info(message: String) = this.logger.info(message)

    override fun warn(message: String) = this.logger.warning(message)

    override fun error(message: String, throwable: Throwable?) =
        if (throwable == null) this.logger.severe(message)
        else this.logger.log(Level.SEVERE, message, throwable)
}
