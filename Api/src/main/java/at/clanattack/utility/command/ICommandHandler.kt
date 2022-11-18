package at.clanattack.utility.command

import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval

interface ICommandHandler {

    @Deprecated("Only commands should be registered", level = DeprecationLevel.ERROR)
    @ScheduledForRemoval(inVersion = "0.6")
    fun registerCommandInstance(instance: Any)

    fun registerCommandInstance(instance: Command)

}