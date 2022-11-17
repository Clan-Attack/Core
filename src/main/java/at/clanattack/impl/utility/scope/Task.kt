package at.clanattack.impl.utility.scope

import at.clanattack.utility.scope.ITask
import org.bukkit.scheduler.BukkitTask
import java.lang.IllegalStateException

class Task : ITask {

    var task: BukkitTask? = null

    private val safeTask: BukkitTask
        get() = task ?: throw IllegalStateException("Runnable must be initialized to run")

    override val id: Int
        get() = safeTask.taskId

    override val sync: Boolean
        get() = safeTask.isSync

    override val cancelled: Boolean
        get() = safeTask.isCancelled

    override fun cancel() = safeTask.cancel()
}