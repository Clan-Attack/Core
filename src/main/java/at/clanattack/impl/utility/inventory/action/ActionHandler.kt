package at.clanattack.impl.utility.inventory.action

import at.clanattack.utility.inventory.ClickEvent
import at.clanattack.utility.inventory.CloseEvent
import at.clanattack.utility.inventory.DismissActionType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class ActionHandler(inventory: Inventory) {

    init {
        actions[inventory] = this
    }

    var dismissActionType: DismissActionType = DismissActionType.LAST_CLOSE

    val itemClick = mutableMapOf<ItemStack, MutableList<ClickEvent>>()
    val anyClick = mutableListOf<ClickEvent>()
    val close = mutableListOf<CloseEvent>()

    var cancelClicks: Boolean = false

    fun dismissActions() {
        itemClick.forEach { (_, list) -> list.clear() }
        itemClick.clear()
        anyClick.clear()
        close.clear()

        val inventors = actions.filter { (_, handler) -> handler == this }.keys
        inventors.forEach { actions.remove(it) }
    }

    companion object {

        val actions = mutableMapOf<Inventory, ActionHandler>()

    }

}