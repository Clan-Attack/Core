package at.clanattack.player.extention

import at.clanattack.player.IPlayerServiceProvider
import java.util.*

object ExtensionPlayerProvider {

    var instance: IPlayerServiceProvider? = null

    private val safeInstance: IPlayerServiceProvider
        get() = instance ?: throw IllegalStateException("Player management not initialized yet")

    fun existsPlayer(uuid: UUID) = safeInstance.existsPlayer(uuid)

    fun createPlayer(uuid: UUID) = safeInstance.createPlayer(uuid)

    fun getPlayer(uuid: UUID) = safeInstance.getPlayer(uuid)

}