@file:Suppress("unused")
package at.clanattack.utility.scope

object ScopeProvider {

    var instance: IScopeHandler? = null

    private val safeInstance: IScopeHandler
        get() = instance ?: throw IllegalStateException("Scopes not initialized yet")

    fun later(delay: Long, task: () -> Unit) = safeInstance.later(delay, task)

    fun tickLater(task: () -> Unit) = safeInstance.tickLater(task)

    fun repeat(delay: Long, period: Long, task: () -> Unit) = safeInstance.timer(delay, period, task)

    fun repeat(period: Long, task: () -> Unit) = repeat(0, period, task)

    fun sync(task: () -> Unit) = safeInstance.sync(task)

    fun laterAsync(delay: Long, task: () -> Unit) = safeInstance.laterAsync(delay, task)

    fun tickLaterAsync(task: () -> Unit) = safeInstance.tickLaterAsync(task)

    fun repeatAsync(delay: Long, period: Long, task: () -> Unit) = safeInstance.timerAsync(delay, period, task)

    fun repeatAsync(period: Long, task: () -> Unit) = repeat(0, period, task)

    fun async(task: () -> Unit) = safeInstance.async(task)

}


fun later(delay: Long, task: () -> Unit) = ScopeProvider.later(delay, task)

fun tickLater(task: () -> Unit) = ScopeProvider.tickLater(task)

fun timer(delay: Long, period: Long, task: () -> Unit) = ScopeProvider.repeat(delay, period, task)

fun timer(period: Long, task: () -> Unit) = timer(0, period, task)

fun sync(task: () -> Unit) = ScopeProvider.sync(task)

fun laterAsync(delay: Long, task: () -> Unit) = ScopeProvider.laterAsync(delay, task)

fun tickLaterAsync(task: () -> Unit) = ScopeProvider.tickLaterAsync(task)

fun timerAsync(delay: Long, period: Long, task: () -> Unit) = ScopeProvider.repeatAsync(delay, period, task)

fun timerAsync(period: Long, task: () -> Unit) = timer(0, period, task)

fun async(task: () -> Unit) = ScopeProvider.async(task)
