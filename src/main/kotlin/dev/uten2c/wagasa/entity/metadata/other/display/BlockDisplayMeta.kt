package dev.uten2c.wagasa.entity.metadata.other.display

import net.minestom.server.entity.Entity
import net.minestom.server.entity.Metadata
import net.minestom.server.instance.block.Block

class BlockDisplayMeta(entity: Entity, metadata: Metadata) : AbstractDisplayMeta(entity, metadata) {
    companion object {
        const val OFFSET = AbstractDisplayMeta.MAX_OFFSET
        const val MAX_OFFSET = OFFSET + 1
    }

    var blockStateId: Int by metadataValue(OFFSET + 0, Block.AIR.registry().stateId(), Metadata::BlockID)
}
