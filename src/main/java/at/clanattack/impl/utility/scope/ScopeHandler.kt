package at.clanattack.impl.utility.scope

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.scope.IScopeHandler
import at.clanattack.utility.scope.ScopeProvider
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class ScopeHandler(private val core: ICore) : IScopeHandler {

    init {
        ScopeProvider.instance = this
    }

    private val plugin: JavaPlugin
        get() = core.javaPlugin

    override fun later(delay: Long, task: () -> Unit) = Task(Bukkit.getScheduler().runTaskLater(plugin, task, delay))

    override fun timer(delay: Long, period: Long, task: () -> Unit) =
        Task(Bukkit.getScheduler().runTaskTimer(plugin, task, delay, period))

    override fun sync(task: () -> Unit) = Task(Bukkit.getScheduler().runTask(plugin, task))

    override fun laterAsync(delay: Long, task: () -> Unit) = Task(Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, delay))

    override fun timerAsync(delay: Long, period: Long, task: () -> Unit) =
        Task(Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, task, delay, period))

    override fun async(task: () -> Unit) = Task(Bukkit.getScheduler().runTaskAsynchronously(plugin, task))

}