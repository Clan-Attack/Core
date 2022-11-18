package at.clanattack.top.utility

import at.clanattack.top.bootstrap.getServiceProvider
import at.clanattack.utility.IUtilityServiceProvider
import at.clanattack.utility.command.Command
import at.clanattack.utility.date.IFormatDateUtil
import at.clanattack.utility.date.IParseDateUtil
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval
import java.util.*

private val provider: IUtilityServiceProvider
    get() = getServiceProvider()

@Suppress("DEPRECATION")
@ScheduledForRemoval(inVersion = "0.6")
@Deprecated("Only commands should be registered", level = DeprecationLevel.ERROR)
fun registerCommandInstance(instance: Any) = provider.commandHandler.registerCommandInstance(instance)

fun registerCommandInstance(instance: Command) = provider.commandHandler.registerCommandInstance(instance)

fun registerListenerInstance(instance: Any) = provider.listenerHandler.registerListenerInstance(instance)

val formatDateUtil: IFormatDateUtil
    get() = provider.formatDateUtil

val parseDateUtil: IParseDateUtil
    get() = provider.parseDateUtil

fun getUUID(name: String) = provider.uuidFetcher.getUUID(name)

fun getName(uuid: UUID) = provider.uuidFetcher.getName(uuid)

fun existsName(name: String) = provider.uuidFetcher.existsName(name)

fun existsUUID(uuid: UUID) = provider.uuidFetcher.existsUUID(uuid)
