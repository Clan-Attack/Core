package at.clanattack.utility.item.variants

import at.clanattack.utility.item.IItemBuilder

interface IArmorStandBuilder : IItemBuilder<IArmorStandBuilder> {

    val invisible: Boolean
    fun setInvisible(invisible: Boolean): IArmorStandBuilder

    val noBasePlate: Boolean
    fun setNoBasePlate(noBasePlate: Boolean): IArmorStandBuilder

    val showArms: Boolean
    fun setShowArms(showArms: Boolean): IArmorStandBuilder

    val small: Boolean
    fun setSmall(small: Boolean): IArmorStandBuilder

    val marker: Boolean
    fun setMarker(marker: Boolean): IArmorStandBuilder

}