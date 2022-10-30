package at.clanattack.utility.inventory.variant

import at.clanattack.utility.inventory.IInventoryBuilder
import org.bukkit.inventory.Merchant
import org.bukkit.inventory.MerchantRecipe

interface IMerchantInventoryBuilder : IInventoryBuilder<IMerchantInventoryBuilder> {

    val selectedRecipeIndex: Int
    val selectedRecipe: MerchantRecipe?
    val merchant: Merchant

}