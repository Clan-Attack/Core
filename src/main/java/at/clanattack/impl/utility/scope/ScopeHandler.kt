package at.clanattack.impl.utility.scope

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.scope.IScopeHandler
import at.clanattack.utility.scope.ITask
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

class ScopeHandler(private val core: ICore) : IScopeHandler {

    private val plugin: JavaPlugin
        get() = core.javaPlugin

    fun shutdown() {
        Bukkit.getScheduler().pendingTasks
            .filter { it.owner.name == "Clanattack-Core" }
            .forEach { it.cancel() }
    }

    private fun generateTask(task: ITask.() -> Unit, creator: (TaskRunnable) -> BukkitTask): ITask {
        val taskInstance = Task()
        val bukkitTask = creator(TaskRunnable(taskInstance, task))
        taskInstance.task = bukkitTask
        return taskInstance
    }

    override fun later(delay: Long, task: ITask.() -> Unit) =
        generateTask(task) { Bukkit.getScheduler().runTaskLater(plugin, it, delay) }

    override fun timer(delay: Long, period: Long, task: ITask.() -> Unit) =
        generateTask(task) { Bukkit.getScheduler().runTaskTimer(plugin, it, delay, period) }

    override fun sync(task: ITask.() -> Unit) = generateTask(task) { Bukkit.getScheduler().runTask(plugin, it) }

    override fun laterAsync(delay: Long, task: ITask.() -> Unit) =
        generateTask(task) { Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, it, delay) }

    override fun timerAsync(delay: Long, period: Long, task: ITask.() -> Unit) =
        generateTask(task) { Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, it, delay, period) }

    override fun async(task: ITask.() -> Unit) =
        generateTask(task) { Bukkit.getScheduler().runTaskAsynchronously(plugin, it) }

}