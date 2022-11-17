package at.clanattack.discord.flux

import net.dv8tion.jda.api.interactions.callbacks.IReplyCallback
import reactor.core.publisher.Flux

fun <T : Any, R : Any> Flux<T>.mapAsync(mapper: (T, (R) -> Unit) -> Unit): Flux<R> = Flux.create { sink ->
    this.subscribe { mapper(it) { mapped -> sink.next(mapped) } }
}

fun Flux<out IReplyCallback>.deferReply(ephemeral: Boolean = true) =
    this.mapAsync { replyCallback, call -> replyCallback.deferReply(ephemeral).queue { call(it) } }

fun <T : IReplyCallback> Flux<T>.deferReplyKeepEvent(ephemeral: Boolean = true) =
    this.mapAsync { replyCallback, call -> replyCallback.deferReply(ephemeral).queue { call(replyCallback to it) } }
