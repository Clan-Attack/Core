package at.clanattack.xjkl.wait

import org.jetbrains.annotations.ApiStatus
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Deprecated("In favor of Lock", ReplaceWith("Lock"))
@ApiStatus.ScheduledForRemoval(inVersion = "0.4")
class Wait {

    private var run = false
    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    val isFinished: Boolean
        get() = run

    fun await() {
        if (run) return

        lock.withLock {
            condition.await()
        }
    }

    fun signal() {
        run = true
        lock.withLock {
            condition.signalAll()
        }
    }

}