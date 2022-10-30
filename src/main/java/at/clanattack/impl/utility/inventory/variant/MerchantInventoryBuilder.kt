package at.clanattack.impl.utility.inventory.variant

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.variant.IMerchantInventoryBuilder
import at.clanattack.impl.utility.inventory.InventoryBuilder
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.Merchant
import org.bukkit.inventory.MerchantInventory
import org.bukkit.inventory.MerchantRecipe

class MerchantInventoryBuilder(inventory: Inventory, core: ICore) :
    InventoryBuilder<IMerchantInventoryBuilder, MerchantInventory>(inventory, core), IMerchantInventoryBuilder {

    override fun checkInventory(inventory: Inventory) = inventory is MerchantInventory

    override fun getInstance() = this

    override val selectedRecipeIndex: Int
        get() = this.getInventory().selectedRecipeIndex

    override val selectedRecipe: MerchantRecipe?
        get() = this.getInventory().selectedRecipe

    override val merchant: Merchant
        get() = this.getInventory().merchant

}