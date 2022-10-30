package at.clanattack.impl.player

import at.clanattack.bootstrap.ICore
import at.clanattack.bootstrap.provider.AbstractServiceProvider
import at.clanattack.bootstrap.provider.ServiceProvider
import at.clanattack.database.ISurrealServiceProvider
import at.clanattack.player.IPlayer
import at.clanattack.player.IPlayerServiceProvider
import at.clanattack.utility.IUtilityServiceProvider
import at.clanattack.xjkl.future.CompletableFuture
import at.clanattack.xjkl.future.Future
import at.clanattack.impl.player.model.DBPlayer
import java.util.*

@ServiceProvider(IPlayerServiceProvider::class, [ISurrealServiceProvider::class])
class PlayerServiceProvider(core: ICore) : AbstractServiceProvider(core), IPlayerServiceProvider {

    init {
        Actionbar.internalCore = core
    }

    override fun existsPlayer(uuid: UUID) = this.getPlayer(uuid) != null

    override fun existsPlayer(name: String) = this.getPlayer(name) != null

    override fun createPlayer(uuid: UUID): Future<Unit> {
        return this.core.getServiceProvider(ISurrealServiceProvider::class)
            .create("player:`$uuid`", DBPlayer(0, emptyMap()))
    }

    override fun getPlayerAsync(uuid: UUID): Future<IPlayer?> {
        val future = CompletableFuture<IPlayer?>()

        this.core.getServiceProvider(ISurrealServiceProvider::class).select("player:`$uuid`", DBPlayer::class)
            .then { future.complete(if (it.isEmpty()) null else Player(this.core, uuid)) }

        return future
    }

    override fun getPlayerAsync(name: String): Future<IPlayer?> {
        val uuid = this.core.getServiceProvider(IUtilityServiceProvider::class).uuidFetcher.getUUID(name)
            ?: return CompletableFuture<IPlayer?>().complete(null)

        return getPlayerAsync(uuid)
    }


}