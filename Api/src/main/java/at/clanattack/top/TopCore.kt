package at.clanattack.top

import at.clanattack.bootstrap.ICore
import org.jetbrains.annotations.ApiStatus.Internal

object TopCore {

    @Internal
    var internalCore: ICore? = null

    @get:Internal
    val core: ICore
        get() = internalCore ?: throw IllegalStateException("Providing not initialized yet")

}
