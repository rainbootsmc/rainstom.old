package dev.uten2c.wagasa.entity.metadata.other

import net.minestom.server.entity.Entity
import net.minestom.server.entity.Metadata
import net.minestom.server.entity.metadata.EntityMeta

class InteractionMeta(entity: Entity, metadata: Metadata) : EntityMeta(entity, metadata) {
    companion object {
        const val OFFSET = EntityMeta.MAX_OFFSET
        const val MAX_OFFSET = OFFSET + 3
    }

    var width: Float by metadataValue(OFFSET + 0, 1f, Metadata::Float)
    var height: Float by metadataValue(OFFSET + 1, 1f, Metadata::Float)
    var response: Boolean by metadataValue(OFFSET + 2, false, Metadata::Boolean)
}
