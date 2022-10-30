package at.clanattack.xjkl.future

import at.clanattack.xjkl.scope.withThis
import java.util.concurrent.CompletableFuture

open class CompletableFuture<T> : Future<T>(CompletableFuture<T>()) {

    fun complete(value: T): Future<T> = this.withThis { this.future.complete(value) }

    fun fail(e: Throwable): Future<T> = this.withThis { this.future.completeExceptionally(e) }

}