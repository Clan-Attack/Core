package at.clanattack.player

import at.clanattack.player.actionbar.ActionbarPriority
import at.clanattack.xjkl.future.Future
import kotlin.reflect.KClass

interface IPlayer {

    val online: Boolean

    val onlineTime: Long
        get() = this.getOnlineTimeAsync().getSync()

    val playerData: Map<String, String?>
        get() = this.getPlayerDataAsync().getSync()

    fun getOnlineTimeAsync(): Future<Long>

    fun sendMessage(key: String, vararg placeholders: String)

    fun sendTitle(
        fadeIn: Number,
        stay: Number,
        fadeOut: Number,
        title: String?,
        subtitle: String?,
        vararg placeholders: String
    )

    fun sendActionbar(priority: ActionbarPriority, stay: Int, key: String, vararg placeholders: String)

    fun sendActionbar(priority: ActionbarPriority, key: String, vararg placeholders: String) =
        sendActionbar(priority, -1, key, *placeholders)

    fun <T : Any> getPlayerData(key: String, clazz: KClass<T>) = this.getPlayerDataAsync(key, clazz).getSync()

    fun <T : Any> getPlayerDataAsync(key: String, clazz: KClass<T>): Future<T?>

    fun <T : Any> getPlayerData(key: String, default: T, clazz: KClass<T>) =
        this.getPlayerDataAsync(key, default, clazz).getSync()

    fun <T : Any> getPlayerDataAsync(key: String, default: T, clazz: KClass<T>): Future<T>

    fun setPlayerData(key: String, value: Any) = this.setPlayerDataAsync(key, value).getSync()

    fun setPlayerDataAsync(key: String, value: Any): Future<Unit>

    fun hasPlayerData(key: String) = this.hasPlayerDataAsync(key).getSync()

    fun hasPlayerDataAsync(key: String): Future<Boolean>

    fun removePlayerData(key: String) = this.removePlayerDataAsync(key).getSync()

    fun removePlayerDataAsync(key: String): Future<Unit>

    fun getPlayerDataAsync(): Future<Map<String, String?>>

}