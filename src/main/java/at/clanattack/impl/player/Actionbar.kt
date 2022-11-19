package at.clanattack.impl.player

import at.clanattack.bootstrap.ICore
import at.clanattack.player.actionbar.ActionbarPriority
import at.clanattack.utility.IUtilityServiceProvider
import at.clanattack.utility.scope.ITask
import at.clanattack.xjkl.scope.asExpr
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import java.util.*

data class ActionbarInformation(val message: Component, val endTime: Long, val priority: ActionbarPriority)

object Actionbar {

    internal var internalCore: ICore? = null
    val core: ICore
        get() = internalCore ?: throw IllegalStateException("Actionbar not initialized yet.")

    private var running = false
    private val actionbar = mutableMapOf<UUID, ActionbarInformation>()

    operator fun contains(uuid: UUID) = actionbar.contains(uuid)

    operator fun get(uuid: UUID) = actionbar[uuid]

    operator fun set(uuid: UUID, actionbarInformation: ActionbarInformation) =
        asExpr {
            actionbar[uuid] = actionbarInformation
            if (!running) start()
        }

    private var task: ITask? = null

    private fun start() {
        if (this.running) return

        running = true
        run()
    }

    private fun stop() {
        if (!this.running || this.task == null) return
        this.task!!.cancel()
    }

    private fun run() {
        this.task = this.core.getServiceProvider(IUtilityServiceProvider::class).scopeHandler.timerAsync(5) {
            if (actionbar.isEmpty()) {
                stop()
                Thread.currentThread().interrupt()
                return@timerAsync
            }

            val forRemoval = mutableListOf<UUID>()
            this@Actionbar.actionbar.forEach { (uuid, information) ->
                if (information.endTime < System.currentTimeMillis()) {
                    forRemoval.add(uuid)
                    return@forEach
                }

                val player = Bukkit.getPlayer(uuid)
                if (player == null) {
                    forRemoval.add(uuid)
                    return@forEach
                }

                player.sendActionBar(information.message)
            }

            forRemoval.forEach { this@Actionbar.actionbar.remove(it) }
        }
    }


}