package at.clanattack.impl.utility.item

import com.destroystokyo.paper.inventory.meta.ArmorStandMeta
import at.clanattack.bootstrap.ICore
import at.clanattack.utility.item.IItemBuilder
import at.clanattack.utility.item.IItemHandler
import at.clanattack.utility.item.ItemProvider
import at.clanattack.utility.item.variants.*
import at.clanattack.impl.utility.item.map.MapViewBuilder
import at.clanattack.impl.utility.item.variant.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.*
import org.bukkit.map.MapView
import kotlin.reflect.KClass

class ItemHandler(private val core: ICore) : IItemHandler {

    init {
        ItemProvider.instance = this
    }

    override fun createItem(stack: ItemStack): IItemBuilder<*> {
        val itemMeta =
            stack.itemMeta ?: throw IllegalStateException("Can't create ItemBuilder of ItemStack without ItemMeta.")

        return ((itemBuilders.values.firstOrNull { it.second.isInstance(itemMeta) }?.first)
            ?: PlainItemBuilder::class).java.getDeclaredConstructor(ItemStack::class.java, ICore::class.java)
            .newInstance(stack, core)
    }

    override fun createNullableItem(stack: ItemStack?): IItemBuilder<*>? {
        if (stack == null) return null

        val itemMeta =
            stack.itemMeta ?: throw IllegalStateException("Can't create ItemBuilder of ItemStack without ItemMeta.")

        return ((itemBuilders.values.firstOrNull { it.second.isInstance(itemMeta) }?.first)
            ?: PlainItemBuilder::class).java.getDeclaredConstructor(ItemStack::class.java, ICore::class.java)
            .newInstance(stack, core)
    }

    override fun createItem(builder: IItemBuilder<*>) = createItem(builder.build().clone())

    override fun createItem(material: Material) = createItem(ItemStack(material))

    override fun createMapView(mapView: MapView?, world: World?) =
        MapViewBuilder(
            mapView ?: Bukkit.createMap(
                world ?: Bukkit.getWorld("world") ?: Bukkit.getWorlds()[0]
            )
        )

    companion object {

        val itemBuilders =
            mapOf<KClass<out IItemBuilder<*>>, Pair<KClass<out ItemBuilder<*, *>>, KClass<out ItemMeta>>>(
                IArmorStandBuilder::class to (ArmorStandBuilder::class to ArmorStandMeta::class),
                IAxolotlBucketBuilder::class to (AxolotlBucketBuilder::class to AxolotlBucketMeta::class),
                IBannerBuilder::class to (BannerBuilder::class to BannerMeta::class),
                IBookBuilder::class to (BookBuilder::class to BookMeta::class),
                IBundleBuilder::class to (BundleBuilder::class to BundleMeta::class),
                ICompassBuilder::class to (CompassBuilder::class to CompassMeta::class),
                ICrossbowBuilder::class to (CrossbowBuilder::class to CrossbowMeta::class),
                IEnchantmentStorageBuilder::class to (EnchantmentStorageBuilder::class to EnchantmentStorageMeta::class),
                IFireworkBuilder::class to (FireworkBuilder::class to FireworkMeta::class),
                IFireworkEffectBuilder::class to (FireworkEffectBuilder::class to FireworkEffectMeta::class),
                IKnowledgeBookBuilder::class to (KnowledgeBookBuilder::class to KnowledgeBookMeta::class),
                ILeatherArmorBuilder::class to (LeatherArmorBuilder::class to LeatherArmorMeta::class),
                IMapBuilder::class to (MapBuilder::class to MapMeta::class),
                IPotionBuilder::class to (PotionBuilder::class to PotionMeta::class),
                ISkullBuilder::class to (SkullBuilder::class to SkullMeta::class),
                ISuspiciousStewBuilder::class to (SuspiciousStewBuilder::class to SuspiciousStewMeta::class),
                ITropicalFishBucketBuilder::class to (TropicalFishBucketBuilder::class to TropicalFishBucketMeta::class),

                IDamageableBuilder::class to (DamageableBuilder::class to Damageable::class),
                IRepairableBuilder::class to (RepairableBuilder::class to Repairable::class),
                IBlockDataBuilder::class to (BlockDataBuilder::class to BlockDataMeta::class),
                IBlockStateBuilder::class to (BlockStateBuilder::class to BlockStateMeta::class),

                IItemBuilder::class to (PlainItemBuilder::class to ItemMeta::class),
                IPlainItemBuilder::class to (PlainItemBuilder::class to ItemMeta::class),
            )

    }

}