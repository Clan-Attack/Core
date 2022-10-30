package at.clanattack.utility.item

import com.destroystokyo.paper.Namespaced
import com.google.common.collect.Multimap
import at.clanattack.utility.item.variants.*
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType
import kotlin.reflect.KClass

interface IItemBuilder<Builder : IItemBuilder<Builder>> {

    val material: Material
    fun setMaterial(material: Material): Builder

    val amount: Int
    fun setAmount(amount: Int): Builder

    val hasDisplayName: Boolean
    val displayName: Component?
    fun setDisplayName(displayName: Component): Builder

    val hasLore: Boolean
    val lore: List<Component>?
    fun setLore(lore: List<Component>): Builder
    fun setLore(vararg lore: Component) = setLore(lore.toList())
    fun setLore(index: Int, lore: Component): Builder
    fun addLore(lore: List<Component>): Builder
    fun addLore(vararg lore: Component) = addLore(lore.toList())
    fun removeLore(index: Int): Builder

    val hasCustomModelData: Boolean
    val customModelData: Int
    fun setCustomModelData(customModelData: Int): Builder
    fun removeCustomModelData(): Builder

    val hasEnchantments: Boolean
    val enchantments: Map<Enchantment, Int>
    fun hasEnchantment(enchant: Enchantment): Boolean
    fun getEnchantmentLevel(enchant: Enchantment): Int
    fun hasConflictingEnchantment(enchantment: Enchantment): Boolean
    fun addEnchantment(enchantment: Enchantment, level: Int): Builder
    fun removeEnchantment(enchantment: Enchantment): Builder
    fun clearEnchantments(): Builder

    val itemFlags: List<ItemFlag>
    fun hasItemFlag(flag: ItemFlag): Boolean
    fun addItemFlags(vararg flags: ItemFlag): Builder
    fun removeItemFlags(vararg flags: ItemFlag): Builder
    fun addItemFlags(flags: List<ItemFlag>) = addItemFlags(*flags.toTypedArray())
    fun removeItemFlags(flags: List<ItemFlag>) = removeItemFlags(*flags.toTypedArray())
    fun hideAll() = addItemFlags(*ItemFlag.values())

    val isUnbreakable: Boolean
    fun setUnbreakable(unbreakable: Boolean): Builder

    val hasAttributeModifiers: Boolean
    val attributeModifiers: Multimap<Attribute, AttributeModifier>?
    fun getAttributeModifier(slot: EquipmentSlot): Multimap<Attribute, AttributeModifier>
    fun getAttributeModifier(attribute: Attribute): Collection<AttributeModifier>?
    fun addAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Builder
    fun setAttributeModifier(modifiers: Multimap<Attribute, AttributeModifier>): Builder
    fun removeAttributeModifier(attribute: Attribute): Builder
    fun removeAttributeModifier(slot: EquipmentSlot): Builder
    fun removeAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Builder
    fun clearAttributeModifiers(): Builder

    val dataContainer: PersistentDataContainer
    val dataKeys: List<NamespacedKey>
    val isDataEmpty: Boolean
    fun hasData(key: NamespacedKey): Boolean
    fun removeData(key: NamespacedKey): Builder

    fun <Z : Any> setData(key: NamespacedKey, type: PersistentDataType<*, Z>, value: Z): Builder
    fun setData(key: NamespacedKey, value: Byte) = setData(key, PersistentDataType.BYTE, value)
    fun setData(key: NamespacedKey, value: Short) = setData(key, PersistentDataType.SHORT, value)
    fun setData(key: NamespacedKey, value: Int) = setData(key, PersistentDataType.INTEGER, value)
    fun setData(key: NamespacedKey, value: Long) = setData(key, PersistentDataType.LONG, value)
    fun setData(key: NamespacedKey, value: Float) = setData(key, PersistentDataType.FLOAT, value)
    fun setData(key: NamespacedKey, value: Double) = setData(key, PersistentDataType.DOUBLE, value)
    fun setData(key: NamespacedKey, value: String) = setData(key, PersistentDataType.STRING, value)
    fun setData(key: NamespacedKey, value: ByteArray) = setData(key, PersistentDataType.BYTE_ARRAY, value)
    fun setData(key: NamespacedKey, value: IntArray) = setData(key, PersistentDataType.INTEGER_ARRAY, value)
    fun setData(key: NamespacedKey, value: LongArray) = setData(key, PersistentDataType.LONG_ARRAY, value)

    fun <Z : Any> getData(key: NamespacedKey, type: PersistentDataType<*, Z>): Z?
    fun getByteData(key: NamespacedKey): Byte? = getData(key, PersistentDataType.BYTE)
    fun getShortData(key: NamespacedKey): Short? = getData(key, PersistentDataType.SHORT)
    fun getIntData(key: NamespacedKey): Int? = getData(key, PersistentDataType.INTEGER)
    fun getLongData(key: NamespacedKey): Long? = getData(key, PersistentDataType.LONG)
    fun getFloatData(key: NamespacedKey): Float? = getData(key, PersistentDataType.FLOAT)
    fun getDoubleData(key: NamespacedKey): Double? = getData(key, PersistentDataType.DOUBLE)
    fun getStringData(key: NamespacedKey): String? = getData(key, PersistentDataType.STRING)
    fun getByteArrayData(key: NamespacedKey): ByteArray? = getData(key, PersistentDataType.BYTE_ARRAY)
    fun getIntArrayData(key: NamespacedKey): IntArray? = getData(key, PersistentDataType.INTEGER_ARRAY)
    fun getLongArrayData(key: NamespacedKey): LongArray? = getData(key, PersistentDataType.LONG_ARRAY)

    fun <Z : Any> getData(key: NamespacedKey, type: PersistentDataType<*, Z>, default: Z): Z
    fun getByteData(key: NamespacedKey, default: Byte): Byte = getData(key, PersistentDataType.BYTE, default)
    fun getShortData(key: NamespacedKey, default: Short): Short = getData(key, PersistentDataType.SHORT, default)
    fun getIntData(key: NamespacedKey, default: Int): Int = getData(key, PersistentDataType.INTEGER, default)
    fun getLongData(key: NamespacedKey, default: Long): Long = getData(key, PersistentDataType.LONG, default)
    fun getFloatData(key: NamespacedKey, default: Float): Float = getData(key, PersistentDataType.FLOAT, default)
    fun getDoubleData(key: NamespacedKey, default: Double): Double = getData(key, PersistentDataType.DOUBLE, default)
    fun getStringData(key: NamespacedKey, default: String): String = getData(key, PersistentDataType.STRING, default)
    fun getByteArrayData(key: NamespacedKey, default: ByteArray): ByteArray =
        getData(key, PersistentDataType.BYTE_ARRAY, default)

    fun getIntArrayData(key: NamespacedKey, default: IntArray): IntArray =
        getData(key, PersistentDataType.INTEGER_ARRAY, default)

    fun getLongArrayData(key: NamespacedKey, default: LongArray): LongArray =
        getData(key, PersistentDataType.LONG_ARRAY, default)

    val hasDestroyableKeys: Boolean
    val destroyableKeys: List<Namespaced>
    fun setDestroyableKeys(canDestroy: List<Namespaced>): Builder

    val hasPlaceableKeys: Boolean
    val placeableKeys: List<Namespaced>
    fun setPlaceableKeys(canPlaceOn: List<Namespaced>): Builder

    fun <T : IItemBuilder<*>> asBuilder(builder: KClass<T>): T
    fun asArmorStandBuilder() = asBuilder(IArmorStandBuilder::class)
    fun asAxolotlBucketBuilder() = asBuilder(IAxolotlBucketBuilder::class)
    fun asBannerBuilder() = asBuilder(IBannerBuilder::class)
    fun asBlockDataBuilder() = asBuilder(IBlockDataBuilder::class)
    fun asBlockStateBuilder() = asBuilder(IBlockStateBuilder::class)
    fun asBookBuilder() = asBuilder(IBookBuilder::class)
    fun asBundleBuilder() = asBuilder(IBundleBuilder::class)
    fun asCompassBuilder() = asBuilder(ICompassBuilder::class)
    fun asCrossbowBuilder() = asBuilder(ICrossbowBuilder::class)
    fun asDamageableBuilder() = asBuilder(IDamageableBuilder::class)
    fun asEnchantmentStorageBuilder() = asBuilder(IEnchantmentStorageBuilder::class)
    fun asFireworkBuilder() = asBuilder(IFireworkBuilder::class)
    fun asFireworkEffectBuilder() = asBuilder(IFireworkEffectBuilder::class)
    fun asKnowledgeBookBuilder() = asBuilder(IKnowledgeBookBuilder::class)
    fun asLeatherArmorBuilder() = asBuilder(ILeatherArmorBuilder::class)
    fun asMapBuilderBuilder() = asBuilder(IMapBuilder::class)
    fun asPotionBuilder() = asBuilder(IPotionBuilder::class)
    fun asRepairableBuilder() = asBuilder(IRepairableBuilder::class)
    fun asSkullBuilder() = asBuilder(ISkullBuilder::class)
    fun asSuspiciousStewBuilder() = asBuilder(ISuspiciousStewBuilder::class)
    fun asTropicalFishBucketBuilder() = asBuilder(ITropicalFishBucketBuilder::class)

    fun build(): ItemStack

}