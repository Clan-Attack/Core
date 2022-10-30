package at.clanattack.impl.utility.inventory.action

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.DismissActionType
import at.clanattack.utility.listener.ListenerTrigger
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class ActionListener(private val core: ICore) {

    @ListenerTrigger(InventoryClickEvent::class)
    fun inventoryClick(event: InventoryClickEvent) {
        if (event.whoClicked !is Player) return
        val player = event.whoClicked as Player

        val handler = ActionHandler.actions[event.inventory] ?: return
        if (handler.dismissActionType == DismissActionType.FIRST_ACTION) handler.dismissActions()

        if (handler.cancelClicks) event.isCancelled = true

        handler.anyClick.forEach { it(player, event) }

        val items = handler.itemClick[event.currentItem] ?: return
        items.forEach { it(player, event) }
    }

    @ListenerTrigger(InventoryCloseEvent::class)
    fun inventoryClose(event: InventoryCloseEvent) {
        if (event.player !is Player) return
        val player = event.player as Player

        val handler = ActionHandler.actions[event.inventory] ?: return
        if (handler.dismissActionType == DismissActionType.FIRST_ACTION) handler.dismissActions()

        handler.close.forEach { it(player, event) }
        if (handler.dismissActionType == DismissActionType.ANY_CLOSE) handler.dismissActions()
        if (handler.dismissActionType == DismissActionType.LAST_CLOSE && event.inventory.viewers.size <= 1)
            handler.dismissActions()
    }

}