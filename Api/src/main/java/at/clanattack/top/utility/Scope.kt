package at.clanattack.top.utility

import at.clanattack.top.bootstrap.getServiceProvider
import at.clanattack.utility.IUtilityServiceProvider
import at.clanattack.utility.scope.IScopeHandler
import at.clanattack.utility.scope.ITask

private val scopeHandler: IScopeHandler
    get() = getServiceProvider<IUtilityServiceProvider>().scopeHandler

fun later(delay: Long, task: ITask.() -> Unit) = scopeHandler.later(delay, task)

fun tickLater(task: ITask.() -> Unit) = scopeHandler.tickLater(task)

fun timer(delay: Long, period: Long, task: ITask.() -> Unit) = scopeHandler.timer(delay, period, task)

fun timer(period: Long, task: ITask.() -> Unit) = scopeHandler.timer(period, task)

fun sync(task: ITask.() -> Unit) = scopeHandler.sync(task)

fun laterAsync(delay: Long, task: ITask.() -> Unit) = scopeHandler.laterAsync(delay, task)

fun tickLaterAsync(task: ITask.() -> Unit) = scopeHandler.tickLaterAsync(task)

fun timerAsync(delay: Long, period: Long, task: ITask.() -> Unit) = scopeHandler.timerAsync(delay, period, task)

fun timerAsync(period: Long, task: ITask.() -> Unit) = scopeHandler.timerAsync(period, task)

fun async(task: ITask.() -> Unit) = scopeHandler.async(task)