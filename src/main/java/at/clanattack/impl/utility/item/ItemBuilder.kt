package at.clanattack.impl.utility.item

import com.destroystokyo.paper.Namespaced
import com.google.common.collect.Multimap
import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.IItemBuilder
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass
import kotlin.reflect.cast

abstract class ItemBuilder<Interface : IItemBuilder<Interface>, Meta : ItemMeta>(
    protected val itemStack: ItemStack,
    protected val core: ICore
) : IItemBuilder<Interface> {

    init {
        val meta = this.itemStack.itemMeta
        if (!this.checkMeta(meta)) throw IllegalStateException("Wrong type of item builder")
    }

    override val material: Material
        get() = this.itemStack.type

    override fun setMaterial(material: Material) = this.modifyItemStack { it.type = material }

    override val amount: Int
        get() = this.itemStack.amount

    override fun setAmount(amount: Int) = this.modifyItemStack { it.amount = amount }

    override val hasDisplayName: Boolean
        get() = this.getMeta().hasDisplayName()

    override val displayName: Component?
        get() = this.getMeta().displayName()

    override fun setDisplayName(displayName: Component) = this.modifyMeta { it.displayName(displayName) }

    override val hasLore: Boolean
        get() = this.getMeta().hasLore()

    override val lore: List<Component>?
        get() = this.getMeta().lore()

    override fun setLore(lore: List<Component>) = this.modifyMeta { it.lore(lore) }

    override fun setLore(index: Int, lore: Component): Interface {
        val loreList = this.lore?.toMutableList()
        if (loreList != null && loreList.size > index) {
            loreList[index] = lore
            this.setLore(loreList)
        }

        return this.getInstance()
    }

    override fun addLore(lore: List<Component>): Interface {
        val loreList = this.lore?.toMutableList() ?: mutableListOf()
        loreList.addAll(lore)
        this.setLore(loreList)

        return this.getInstance()
    }

    override fun removeLore(index: Int): Interface {
        val loreList = this.lore?.toMutableList()
        if (loreList != null && loreList.size > index) {
            loreList.removeAt(index)
            this.setLore(loreList)
        }

        return this.getInstance()
    }

    override val hasCustomModelData: Boolean
        get() = this.getMeta().hasCustomModelData()

    override val customModelData: Int
        get() = this.getMeta().customModelData

    override fun setCustomModelData(customModelData: Int) = this.modifyMeta { it.setCustomModelData(customModelData) }

    override fun removeCustomModelData() = this.modifyMeta { it.setCustomModelData(null) }

    override val hasEnchantments: Boolean
        get() = this.getMeta().hasEnchants()

    override val enchantments: Map<Enchantment, Int>
        get() = this.getMeta().enchants

    override fun hasEnchantment(enchant: Enchantment) = this.getMeta().hasEnchant(enchant)

    override fun getEnchantmentLevel(enchant: Enchantment) = this.getMeta().getEnchantLevel(enchant)

    override fun hasConflictingEnchantment(enchantment: Enchantment) = this.getMeta().hasConflictingEnchant(enchantment)

    override fun addEnchantment(enchantment: Enchantment, level: Int) =
        this.modifyMeta { it.addEnchant(enchantment, level, true) }

    override fun removeEnchantment(enchantment: Enchantment) = this.modifyMeta { it.removeEnchant(enchantment) }

    override fun clearEnchantments() = this.modifyMeta {
        it.enchants.forEach { (enchantment, _) -> it.removeEnchant(enchantment) }
    }

    override val itemFlags: List<ItemFlag>
        get() = this.getMeta().itemFlags.toList()

    override fun hasItemFlag(flag: ItemFlag) = this.getMeta().hasItemFlag(flag)

    override fun addItemFlags(vararg flags: ItemFlag) = this.modifyMeta { it.addItemFlags(*flags) }

    override fun removeItemFlags(vararg flags: ItemFlag) = this.modifyMeta { it.removeItemFlags(*flags) }

    override val isUnbreakable: Boolean
        get() = this.getMeta().isUnbreakable

    override fun setUnbreakable(unbreakable: Boolean) = this.modifyMeta { it.isUnbreakable = unbreakable }

    override val hasAttributeModifiers: Boolean
        get() = this.getMeta().hasAttributeModifiers()

    override val attributeModifiers: Multimap<Attribute, AttributeModifier>?
        get() = this.getMeta().attributeModifiers

    override fun getAttributeModifier(slot: EquipmentSlot): Multimap<Attribute, AttributeModifier> =
        this.getMeta().getAttributeModifiers(slot)

    override fun getAttributeModifier(attribute: Attribute): Collection<AttributeModifier>? =
        this.getMeta().getAttributeModifiers(attribute)

    override fun addAttributeModifier(attribute: Attribute, modifier: AttributeModifier) =
        this.modifyMeta { it.addAttributeModifier(attribute, modifier) }

    override fun setAttributeModifier(modifiers: Multimap<Attribute, AttributeModifier>) =
        this.modifyMeta { it.attributeModifiers = modifiers }

    override fun removeAttributeModifier(attribute: Attribute) =
        this.modifyMeta { it.removeAttributeModifier(attribute) }

    override fun removeAttributeModifier(slot: EquipmentSlot) = this.modifyMeta { it.removeAttributeModifier(slot) }

    override fun removeAttributeModifier(attribute: Attribute, modifier: AttributeModifier) =
        this.modifyMeta { it.removeAttributeModifier(attribute, modifier) }

    override fun clearAttributeModifiers() = this.modifyMeta {
        it.attributeModifiers?.keys()?.forEach { attribute -> it.removeAttributeModifier(attribute) }
    }

    override val dataContainer: PersistentDataContainer
        get() = this.getMeta().persistentDataContainer

    private fun modifyDataContainer(consumer: (PersistentDataContainer) -> Unit) = this.modifyMeta {
        val container = it.persistentDataContainer
        consumer(container)
    }

    override val dataKeys: List<NamespacedKey>
        get() = this.dataContainer.keys.toList()

    override val isDataEmpty: Boolean
        get() = this.dataContainer.isEmpty

    override fun hasData(key: NamespacedKey) = this.dataContainer.has(key)

    override fun removeData(key: NamespacedKey) = this.modifyDataContainer { it.remove(key) }

    override fun <Z : Any> setData(key: NamespacedKey, type: PersistentDataType<*, Z>, value: Z) =
        this.modifyDataContainer { it.set(key, type, value) }

    override fun <Z : Any> getData(key: NamespacedKey, type: PersistentDataType<*, Z>) =
        this.dataContainer.get(key, type)

    override fun <Z : Any> getData(key: NamespacedKey, type: PersistentDataType<*, Z>, default: Z) =
        this.dataContainer.getOrDefault(key, type, default)

    override val hasDestroyableKeys: Boolean
        get() = this.getMeta().hasDestroyableKeys()

    override val destroyableKeys: List<Namespaced>
        get() = this.getMeta().destroyableKeys.toList()

    override fun setDestroyableKeys(canDestroy: List<Namespaced>) =
        this.modifyMeta { it.setDestroyableKeys(canDestroy) }

    override val hasPlaceableKeys: Boolean
        get() = this.getMeta().hasPlaceableKeys()

    override val placeableKeys: List<Namespaced>
        get() = this.getMeta().placeableKeys.toList()

    override fun setPlaceableKeys(canPlaceOn: List<Namespaced>) = this.modifyMeta { it.setPlaceableKeys(canPlaceOn) }

    override fun <T : IItemBuilder<*>> asBuilder(builder: KClass<T>): T {
        if (builder.isInstance(this)) return builder.cast(this)

        val builderInformation =
            ItemHandler.itemBuilders[builder] ?: throw IllegalStateException("All ItemBuilders should be registered")

        if (builderInformation.second.isInstance(this.getMeta())) {
            return builder.cast(
                builderInformation.first.java
                    .getDeclaredConstructor(ItemStack::class.java, ICore::class.java)
                    .newInstance(this.itemStack, this.core)
            )
        }


        throw IllegalArgumentException("Can not cast to class witch is not implemented.")
    }

    override fun build() = this.itemStack

    private fun modifyItemStack(consumer: (ItemStack) -> Unit): Interface {
        consumer(this.itemStack)
        return this.getInstance()
    }

    protected fun modifyMeta(consumer: (Meta) -> Unit): Interface {
        val meta = this.itemStack.itemMeta ?: throw IllegalStateException("Can't create builder for item without meta.")

        @Suppress("UNCHECKED_CAST")
        consumer(meta as Meta)

        this.itemStack.itemMeta = meta
        return this.getInstance()
    }

    protected fun getMeta(): Meta {
        val meta = this.itemStack.itemMeta ?: throw IllegalStateException("Can't create builder for item without meta.")

        @Suppress("UNCHECKED_CAST")
        return meta as Meta
    }

    abstract fun checkMeta(itemMeta: ItemMeta): Boolean

    abstract fun getInstance(): Interface

}