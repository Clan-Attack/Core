package at.clanattack.utility.item.extention

import at.clanattack.bootstrap.ICore
import at.clanattack.utility.IUtilityServiceProvider
import at.clanattack.utility.item.IItemBuilder
import org.bukkit.inventory.ItemStack

fun Collection<ItemStack>.builder(core: ICore) =
    this.map { core.getServiceProvider(IUtilityServiceProvider::class).itemHandler.createItem(it) }

fun Collection<IItemBuilder<*>>.build() = this.map { it.build() }

fun Array<out ItemStack>.builder(core: ICore) =
    this.map { core.getServiceProvider(IUtilityServiceProvider::class).itemHandler.createItem(it) }
        .toTypedArray()

fun Array<out IItemBuilder<*>>.build() = this.map { it.build() }.toTypedArray()

fun Array<out IItemBuilder<*>?>.buildNullable() = this.map { it?.build() }.toTypedArray()

fun Array<out ItemStack?>.nullableBuilder(core: ICore) =
    this.map { core.getServiceProvider(IUtilityServiceProvider::class).itemHandler.createNullableItem(it) }
        .toTypedArray()
