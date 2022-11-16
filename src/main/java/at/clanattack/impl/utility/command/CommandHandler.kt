package at.clanattack.impl.utility.command

import io.github.classgraph.ClassGraph
import at.clanattack.bootstrap.ICore
import at.clanattack.impl.bootstrap.util.annotation.AnnotationScanner
import at.clanattack.message.IMessageServiceProvider
import at.clanattack.utility.command.Command
import at.clanattack.utility.command.ICommandHandler
import at.clanattack.utility.listener.ListenerTrigger
import at.clanattack.xjkl.extention.inArray
import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.player.PlayerCommandSendEvent

class CommandHandler(private val core: ICore) : ICommandHandler {

    private val whitelisted = arrayOf("stop", "rl")
    private val instances = mutableMapOf<Class<*>, Any>()
    private val execution = mutableMapOf<String, CommandExecute>()

    fun registerCommands() {
        this.core.logger.info("Registering commands...")

        (this.core.annotationScanner as AnnotationScanner).loaders.map {
            ClassGraph()
                .overrideClassLoaders(it)
                .enableClassInfo()
                .scan()
                .getClassInfo(Command::class.java.name)
                .subclasses
        }
            .flatten()
            .asSequence()
            .filter { !it.isAbstract }
            .map { it.loadClass() }
            .map { it.asSubclass(Command::class.java) }
            .forEach {
                if (!this.register(it)) return@forEach
                val instance = this.instances[it] as Command

                if (!this.canRegister(instance.name, instance.aliases)) {
                    this.core.logger.error(
                        "Can't register command ${instance.name} in ${it.simpleName}" +
                                " because a command with the same name or alias already exists"
                    )
                    return@forEach
                }

                this.execution[instance.name] = CommandExecute(
                    instance.name,
                    instance.aliases,
                    instance.permission,
                    this.core,
                    instance
                )
            }

        Bukkit.getCommandMap().knownCommands
            .filter { (name, _) -> !name.inArray(whitelisted) }
            .forEach { (_, command) -> command.unregister(Bukkit.getCommandMap()) }

        this.execution.forEach { (_, execution) ->
            Bukkit.getCommandMap().register(this.core.javaPlugin.name, execution)
        }

        this.core.logger.info("Registered ${execution.size} commands.")
    }

    private fun canRegister(name: String, aliases: Array<out String>): Boolean {
        return execution.none { (targetName, execute) ->
            targetName == name ||
                    targetName.inArray(aliases) ||
                    execute.aliases.any {
                        it.inArray(aliases) || it == name
                    }
        }
    }

    private fun register(clazz: Class<*>): Boolean {
        if (clazz in this.instances) return true

        val instance = try {
            clazz.getDeclaredConstructor(ICore::class.java).newInstance(this.core)
        } catch (e: NoSuchMethodException) {
            try {
                clazz.getDeclaredConstructor().newInstance()
            } catch (e: NoSuchMethodException) {
                this.core.logger.error(
                    "Couldn't register command class ${clazz.simpleName} because the class " +
                            "doesn't have any correct constructor."
                )
                null
            }
        } ?: return false

        instances[clazz] = instance
        return true
    }

    private fun getCommand(name: String) = execution
        .asSequence()
        .firstOrNull { (targetName, execute) ->
            targetName == name || name.inArray(execute.aliases.toTypedArray())
        }?.value

    override fun registerCommandInstance(instance: Any) {
        this.instances[instance::class.java] = instance
    }

    @ListenerTrigger(PlayerCommandSendEvent::class)
    fun playerCommandSend(event: PlayerCommandSendEvent) {
        event.commands.removeAll(
            event.commands
                .asSequence()
                .filter { this.getCommand(it) == null && !it.inArray(this.whitelisted) }
                .map { it to this.getCommand(it) }
                .filter { (_, c) -> c == null ||
                        c.permission == null ||
                        c.permission == "" ||
                        event.player.hasPermission(c.permission!!) }
                .map { it.first }
                .toSet()
        )
    }

    @ListenerTrigger(PlayerCommandPreprocessEvent::class)
    fun playerCommandPrePreprocessEvent(event: PlayerCommandPreprocessEvent) {
        val command = event.message.split(" ")[0].run {
            if (this.startsWith("/")) this.replaceFirst("/", "")
            else this
        }


        val bukkitCommand = Bukkit.getCommandMap().getCommand(command)
        if (bukkitCommand == null) {
            event.player.sendMessage(this.core.getServiceProvider(IMessageServiceProvider::class)
                .getMessage("core.utility.command.unknown", "command=>$command"))
            event.isCancelled = true
            return
        }

        val internalCommand = this.getCommand(command) ?: return
        val permission = internalCommand.permission ?: return

        if (permission != "" && !event.player.hasPermission(permission)) {
            event.player.sendMessage(this.core.getServiceProvider(IMessageServiceProvider::class)
                .getMessage("core.utility.command.noPermission", "permission=>$permission"))
            event.isCancelled = true
        }
    }

}