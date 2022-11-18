package at.clanattack.utility.scope

interface IScopeHandler {

    fun later(delay: Long, task: ITask.() -> Unit): ITask

    fun tickLater(task: ITask.() -> Unit): ITask = later(1, task)

    fun timer(delay: Long, period: Long, task: ITask.() -> Unit): ITask

    fun timer(period: Long, task: ITask.() -> Unit) = timer(0, period, task)

    fun sync(task: ITask.() -> Unit): ITask

    fun laterAsync(delay: Long, task: ITask.() -> Unit): ITask

    fun tickLaterAsync(task: ITask.() -> Unit): ITask = laterAsync(1, task)

    fun timerAsync(delay: Long, period: Long, task: ITask.() -> Unit): ITask

    fun timerAsync(period: Long, task: ITask.() -> Unit) = timerAsync(0, period, task)

    fun async(task: ITask.() -> Unit): ITask

}