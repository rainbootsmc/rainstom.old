package dev.uten2c.wagasa.entity.metadata.other.display

import dev.uten2c.wagasa.util.math.Quaternionf
import dev.uten2c.wagasa.util.math.Vec3f
import net.minestom.server.entity.Entity
import net.minestom.server.entity.Metadata
import net.minestom.server.entity.metadata.EntityMeta

abstract class AbstractDisplayMeta(entity: Entity, metadata: Metadata) : EntityMeta(entity, metadata) {
    companion object {
        const val OFFSET = EntityMeta.MAX_OFFSET
        const val MAX_OFFSET = OFFSET + 14
    }

    var interpolationStartDeltaTicks: Int by metadataValue(OFFSET + 0, 0, Metadata::VarInt)
    var interpolationDuration: Int by metadataValue(OFFSET + 1, 0, Metadata::VarInt)
    var translation: Vec3f by metadataValue(OFFSET + 2, Vec3f.ZERO, Metadata::Vec3f)
    var scale: Vec3f by metadataValue(OFFSET + 3, Vec3f.ONE, Metadata::Vec3f)
    var rotationLeft: Quaternionf by metadataValue(OFFSET + 4, Quaternionf(Vec3f.ZERO, 1f), Metadata::Quaternionf)
    var rotationRight: Quaternionf by metadataValue(OFFSET + 5, Quaternionf(Vec3f.ZERO, 1f), Metadata::Quaternionf)
    var billboardConstraints: BillboardConstraints
        get() = BillboardConstraints.values()[metadata.getIndex(OFFSET + 6, 0)]
        set(value) = metadata.setIndex(OFFSET + 6, Metadata.Byte(value.ordinal.toByte()))
    var billboardOverride: Brightness?
        get() {
            val value = metadata.getIndex(OFFSET + 7, -1)
            return if (value == -1) null else Brightness.unpack(value)
        }
        set(value) {
            val int = value?.pack() ?: -1
            metadata.setIndex(OFFSET + 7, Metadata.VarInt(int))
        }
    var viewRange: Float by metadataValue(OFFSET + 8, 1f, Metadata::Float)
    var shadowRadius: Float by metadataValue(OFFSET + 9, 0f, Metadata::Float)
    var shadowStrength: Float by metadataValue(OFFSET + 10, 1f, Metadata::Float)
    var width: Float by metadataValue(OFFSET + 11, 0f, Metadata::Float)
    var height: Float by metadataValue(OFFSET + 12, 0f, Metadata::Float)
    var glowColorOverride: Int by metadataValue(OFFSET + 13, -1, Metadata::VarInt)
}
