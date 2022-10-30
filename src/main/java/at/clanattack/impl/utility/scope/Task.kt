package at.clanattack.impl.utility.scope

import at.clanattack.utility.scope.ITask
import org.bukkit.scheduler.BukkitTask

class Task(private val task: BukkitTask) : ITask {

    override val id: Int
        get() = task.taskId

    override val sync: Boolean
        get() = task.isSync

    override val cancelled: Boolean
        get() = task.isCancelled

    override fun cancel() = task.cancel()
}