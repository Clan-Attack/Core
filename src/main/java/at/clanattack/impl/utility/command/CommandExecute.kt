package at.clanattack.impl.utility.command

import at.clanattack.bootstrap.ICore
import at.clanattack.message.IMessageServiceProvider
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandExecute(
    name: String,
    aliases: Array<out String>,
    permission: String,
    private val core: ICore,
    private val instance: at.clanattack.utility.command.Command
) : Command(name, "", "/$name", aliases.toList()) {

    init {
        this.permission = permission
    }

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>?): Boolean {
        if (this.permission != "" && !sender.hasPermission(this.permission!!)) {
            sender.sendMessage(
                this.core.getServiceProvider(IMessageServiceProvider::class)
                    .getMessage("core.utility.command.noPermission", "permission=>${this.permission!!}")
            )
            return true
        }

        instance.execute(sender, args ?: emptyArray())
        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<out String>?): MutableList<String> {
        val completion = mutableListOf<String>()
        if (this.permission != "" && !sender.hasPermission(this.permission!!)) return completion

        instance.tabComplete(sender, args ?: emptyArray(), completion)
        return completion
    }

}