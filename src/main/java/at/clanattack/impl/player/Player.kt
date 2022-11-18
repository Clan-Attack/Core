package at.clanattack.impl.player

import at.clanattack.bootstrap.ICore
import at.clanattack.database.ISurrealServiceProvider
import at.clanattack.impl.player.model.DBPlayer
import at.clanattack.impl.player.model.PlayerDataUpdate
import at.clanattack.message.IMessageServiceProvider
import at.clanattack.player.IPlayer
import at.clanattack.player.actionbar.ActionbarPriority
import at.clanattack.utility.IUtilityServiceProvider
import at.clanattack.xjkl.extention.supplyNullable
import at.clanattack.xjkl.future.CompletableFuture
import at.clanattack.xjkl.future.Future
import at.clanattack.xjkl.future.ToUnitFuture
import at.clanattack.xjkl.scope.fromT
import at.clanattack.xjkl.scope.toT
import com.surrealdb.driver.model.patch.RemovePatch
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.time.Duration
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

class Player(
    private val core: ICore,
    override val uuid: UUID
) : IPlayer {

    private val surreal: ISurrealServiceProvider
        get() = this.core.getServiceProvider(ISurrealServiceProvider::class)

    override val name: String
        get() = this.core.getServiceProvider(IUtilityServiceProvider::class).uuidFetcher.getName(this.uuid)
            ?: throw java.lang.IllegalStateException("UUID doesn't exist")

    override val bukkit: Player?
        get() = Bukkit.getPlayer(this.uuid)

    override val offlineBukkit: OfflinePlayer
        get() = Bukkit.getOfflinePlayer(this.uuid)

    override val online: Boolean
        get() = Bukkit.getOfflinePlayer(this.uuid).isOnline

    override fun getOnlineTimeAsync(): Future<Long> {
        val future = CompletableFuture<Long>()

        this.surreal.select("player:`${this.uuid}`", DBPlayer::class).then {
            if (it.isEmpty()) {
                future.fail(IllegalStateException("Player was not created"))
                return@then
            }

            future.complete(
                it[0].onlineTime + if (this.online) (System.currentTimeMillis() - (joinTime[this.uuid]
                    ?: System.currentTimeMillis())) else 0L
            )
        }

        return future
    }

    override fun sendMessage(key: String, vararg placeholders: String) = Bukkit.getPlayer(this.uuid)!!
        .sendMessage(this.core.getServiceProvider(IMessageServiceProvider::class).getMessage(key, *placeholders))

    override fun sendTitle(
        fadeIn: Number,
        stay: Number,
        fadeOut: Number,
        title: String?,
        subtitle: String?,
        vararg placeholders: String
    ) = Bukkit.getPlayer(this.uuid)!!.showTitle(
        Title.title(
            if (title == null) Component.text("") else this.core.getServiceProvider(IMessageServiceProvider::class)
                .getMessage(title, *placeholders),
            if (subtitle == null) Component.text("") else this.core.getServiceProvider(IMessageServiceProvider::class)
                .getMessage(subtitle, *placeholders),
            Title.Times.times(
                Duration.ofSeconds(fadeIn.toLong()),
                Duration.ofSeconds(stay.toLong()),
                Duration.ofSeconds(fadeOut.toLong()),
            )
        )
    )

    override fun sendActionbar(
        priority: ActionbarPriority,
        stay: Int,
        key: String,
        vararg placeholders: String
    ) {
        if (this.uuid in Actionbar && Actionbar[uuid]!!.priority.value > priority.value) return

        Actionbar[this.uuid] = ActionbarInformation(
            this.core.getServiceProvider(IMessageServiceProvider::class).getMessage(key, *placeholders),
            System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(stay.toLong()),
            priority
        )
    }

    override fun <T : Any> getPlayerDataAsync(key: String, clazz: KClass<T>): Future<T?> {
        val future = CompletableFuture<T?>()
        this.getPlayerDataAsync().then { data -> future.complete(data[key].supplyNullable { it.toT(clazz.java) }) }
        return future
    }

    override fun <T : Any> getPlayerDataAsync(key: String, default: T, clazz: KClass<T>): Future<T> {
        val future = CompletableFuture<T>()
        this.getPlayerDataAsync()
            .then { data -> future.complete(data[key].supplyNullable { it.toT(clazz.java) } ?: default) }
        return future
    }

    override fun getPlayerDataAsync(): Future<Map<String, String?>> {
        val future = CompletableFuture<Map<String, String?>>()

        this.surreal.select("player:`${this.uuid}`", DBPlayer::class).then {
            if (it.isEmpty()) {
                future.fail(IllegalStateException("Player was not created"))
                return@then
            }

            future.complete(it[0].playerData)
        }

        return future
    }

    override fun setPlayerDataAsync(key: String, value: Any): Future<Unit> {
        return ToUnitFuture(
            this.surreal.change(
                "player:`${this.uuid}`",
                PlayerDataUpdate.create(key, value.fromT()),
                DBPlayer::class
            )
        )
    }

    override fun hasPlayerDataAsync(key: String): Future<Boolean> {
        val future = CompletableFuture<Boolean>()
        this.getPlayerDataAsync()
        return future
    }

    override fun removePlayerDataAsync(key: String): Future<Unit> {
        return this.surreal.patch("player:`${this.uuid}`", listOf(RemovePatch("playerData.$key")))
    }

    companion object {

        val joinTime = mutableMapOf<UUID, Long>()

    }


}