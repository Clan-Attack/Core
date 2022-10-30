package at.clanattack.impl.utility.inventory

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.inventory.IInventoryBuilder
import at.clanattack.utility.inventory.IInventoryHandler
import at.clanattack.utility.inventory.InventoryProvider
import at.clanattack.utility.inventory.variant.*
import at.clanattack.utility.inventory.variant.horse.*
import at.clanattack.impl.utility.inventory.variant.*
import at.clanattack.impl.utility.inventory.variant.horse.*
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.*

class InventoryHandler(private val core: ICore) : IInventoryHandler {

    init {
        InventoryProvider.instance = this
    }

    override fun createInventory(inventory: Inventory): IInventoryBuilder<*> =
        (inventoryBuilders.values.firstOrNull { it.second.isInstance(inventory) }?.first
            ?: PlainInventoryBuilder::class).java.getDeclaredConstructor(Inventory::class.java, ICore::class.java)
            .newInstance(inventory, core)

    override fun createInventory(holder: InventoryHolder?, type: InventoryType) =
        this.createInventory(Bukkit.createInventory(holder, type))

    override fun createInventory(
        holder: InventoryHolder?,
        type: InventoryType,
        title: Component
    ) = this.createInventory(Bukkit.createInventory(holder, type, title))

    override fun createInventory(holder: InventoryHolder?, rows: Int) =
        this.createInventory(Bukkit.createInventory(holder, rows * 9))

    override fun createInventory(holder: InventoryHolder?, rows: Int, title: Component) =
        this.createInventory(Bukkit.createInventory(holder, rows * 9, title))

    companion object {

        val inventoryBuilders =
            mapOf(
                IHorseInventoryBuilder::class to (HorseInventoryBuilder::class to HorseInventory::class),
                ILlamaInventoryBuilder::class to (LlamaInventoryBuilder::class to LlamaInventory::class),
                IAnvilInventoryBuilder::class to (AnvilInventoryBuilder::class to AnvilInventory::class),
                IBeaconInventoryBuilder::class to (BeaconInventoryBuilder::class to BeaconInventory::class),
                IBrewerInventoryBuilder::class to (BrewerInventoryBuilder::class to BrewerInventory::class),
                ICartographyInventoryBuilder::class to (CartographyInventoryBuilder::class to CartographyInventory::class),
                ICraftingInventoryBuilder::class to (CraftingInventoryBuilder::class to CraftingInventory::class),
                IDoubleChestInventoryBuilder::class to (DoubleChestInventoryBuilder::class to DoubleChestInventory::class),
                IEnchantingInventoryBuilder::class to (EnchantingInventoryBuilder::class to EnchantingInventory::class),
                IFurnaceInventoryBuilder::class to (FurnaceInventoryBuilder::class to FurnaceInventory::class),
                ILecternInventoryBuilder::class to (LecternInventoryBuilder::class to LecternInventory::class),
                ILoomInventoryBuilder::class to (LoomInventoryBuilder::class to LoomInventory::class),
                IMerchantInventoryBuilder::class to (MerchantInventoryBuilder::class to MerchantInventory::class),
                IPlayerInventoryBuilder::class to (PlayerInventoryBuilder::class to PlayerInventory::class),
                ISmithingInventoryBuilder::class to (SmithingInventoryBuilder::class to SmithingInventory::class),

                ISaddledHorseInventoryBuilder::class to (SaddledHorseInventoryBuilder::class to SaddledHorseInventory::class),
                IArmoredHorseInventoryBuilder::class to (ArmoredHorseInventoryBuilder::class to ArmoredHorseInventory::class),
                IAbstractHorseInventoryBuilder::class to (AbstractHorseInventoryBuilder::class to AbstractHorseInventory::class),
                IInventoryBuilder::class to (PlainInventoryBuilder::class to Inventory::class),
                IPlainInventoryBuilder::class to (PlainInventoryBuilder::class to Inventory::class),
            )

    }

}