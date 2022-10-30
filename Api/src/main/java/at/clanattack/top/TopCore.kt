package at.clanattack.top

import at.clanattack.bootstrap.ICore

object TopCore {

    var internalCore: ICore? = null

    internal val topCore: ICore
        get() = internalCore ?: throw IllegalStateException("Providing not initialized yet")

}
