package at.clanattack.utility.scope

@Suppress("unused")
interface IScopeHandler {

    fun later(delay: Long, task: () -> Unit): ITask

    fun tickLater(task: () -> Unit): ITask = later(1, task)

    fun timer(delay: Long, period: Long, task: () -> Unit): ITask

    fun timer(period: Long, task: () -> Unit) = timer(0, period, task)

    fun sync(task: () -> Unit): ITask

    fun laterAsync(delay: Long, task: () -> Unit): ITask

    fun tickLaterAsync(task: () -> Unit): ITask = laterAsync(1, task)

    fun timerAsync(delay: Long, period: Long, task: () -> Unit): ITask

    fun timerAsync(period: Long, task: () -> Unit) = timerAsync(0, period, task)

    fun async(task: () -> Unit): ITask

}