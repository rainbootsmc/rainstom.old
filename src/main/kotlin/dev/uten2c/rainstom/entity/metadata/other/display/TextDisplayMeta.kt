package dev.uten2c.rainstom.entity.metadata.other.display

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Metadata

class TextDisplayMeta(entity: Entity, metadata: Metadata) : AbstractDisplayMeta(entity, metadata) {
    companion object {
        const val OFFSET = AbstractDisplayMeta.MAX_OFFSET
        const val MAX_OFFSET = OFFSET + 5
    }

    var text: Component by metadataValue(OFFSET + 0, Component.empty(), Metadata::Chat)
    var lineWidth: Int by metadataValue(OFFSET + 1, 200, Metadata::VarInt)
    var backgroundColor: Int by metadataValue(OFFSET + 2, 0x40000000, Metadata::VarInt)
    var textOpacity: Byte by metadataValue(OFFSET + 3, -1, Metadata::Byte)
    var styleFlags: Byte by metadataValue(OFFSET + 4, 0, Metadata::Byte)
}
