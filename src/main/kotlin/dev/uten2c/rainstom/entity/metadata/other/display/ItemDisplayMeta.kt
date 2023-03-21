package dev.uten2c.rainstom.entity.metadata.other.display

import net.minestom.server.entity.Entity
import net.minestom.server.entity.Metadata
import net.minestom.server.item.ItemStack

class ItemDisplayMeta(entity: Entity, metadata: Metadata) : AbstractDisplayMeta(entity, metadata) {
    companion object {
        const val OFFSET = AbstractDisplayMeta.MAX_OFFSET
        const val MAX_OFFSET = OFFSET + 2
    }

    var itemStack: ItemStack by metadataValue(OFFSET + 0, ItemStack.AIR, Metadata::Slot)
    var itemDisplay: ItemDisplay
        get() = ItemDisplay.values()[metadata.getIndex(OFFSET + 1, 0)]
        set(value) = metadata.setIndex(OFFSET + 1, Metadata.Byte(value.ordinal.toByte()))
}
