package at.clanattack.top.utility

import at.clanattack.top.TopCore
import at.clanattack.utility.IUtilityServiceProvider

fun later(delay: Long, task: () -> Unit) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.later(delay, task)

fun tickLater(task: () -> Unit) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.tickLater(task)

fun timer(delay: Long, period: Long, task: () -> Unit) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.timer(delay, period, task)

fun timer(period: Long, task: () -> Unit) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.timer(period, task)

fun sync(task: () -> Unit) = TopCore.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.sync(task)

fun laterAsync(delay: Long, task: () -> Unit) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.laterAsync(delay, task)

fun tickLaterAsync(task: () -> Unit) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.tickLaterAsync(task)

fun timerAsync(delay: Long, period: Long, task: () -> Unit) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.timerAsync(delay, period, task)

fun timerAsync(period: Long, task: () -> Unit) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.timerAsync(period, task)

fun async(task: () -> Unit) =
    TopCore.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.async(task)