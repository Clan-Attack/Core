package at.clanattack.xjkl.future

import at.clanattack.xjkl.scope.withThis
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

@Suppress("unused", "MemberVisibilityCanBePrivate")
open class Future<T>(protected val future: CompletableFuture<T>) {

    constructor(value: T): this(CompletableFuture()) {
        this.future.complete(value)
    }

    fun getSync(): T = this.future.get()

    fun getSync(timeout: Long, unit: TimeUnit): T = this.future.get(timeout, unit)

    fun getSync(millis: Long): T = this.getSync(millis, TimeUnit.MILLISECONDS)

    fun then(then: (T) -> Unit) = withThis { this.future.thenAccept(then) }

    fun thenRun(then: () -> Unit) = withThis { this.future.thenRun(then) }

    fun catch(exception: (Throwable) -> Unit): Future<T> {
        this.future.exceptionally {
            exception(it)
            null
        }
        return this
    }

}