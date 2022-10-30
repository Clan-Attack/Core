package at.clanattack.utility.command

import org.bukkit.command.CommandSender

abstract class Command(
    val name: String,
    vararg val aliases: String = emptyArray(),
    val permission: String = ""
) {

    abstract fun execute(sender: CommandSender, args: Array<out String>)

    @Suppress("EmptyMethod")
    open fun tabComplete(sender: CommandSender, args: Array<out String>, completions: MutableList<String>) {}

}