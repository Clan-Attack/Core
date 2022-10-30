package at.clanattack.xjkl.future

class ToUnitFuture(waitFuture: Future<*>) : CompletableFuture<Unit>() {

    constructor(waitFuture: java.util.concurrent.CompletableFuture<*>) : this(Future(waitFuture))

    init {
        waitFuture.thenRun { complete(Unit) }
        waitFuture.catch { fail(it) }
    }

}