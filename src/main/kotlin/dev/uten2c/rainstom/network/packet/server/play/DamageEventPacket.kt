package dev.uten2c.rainstom.network.packet.server.play

import dev.uten2c.rainstom.util.network.*
import net.minestom.server.coordinate.Point
import net.minestom.server.coordinate.Vec
import net.minestom.server.network.NetworkBuffer
import net.minestom.server.network.packet.server.ServerPacket
import net.minestom.server.network.packet.server.ServerPacketIdentifier

data class DamageEventPacket(val entityId: Int, val sourceTypeId: Int, val sourceCauseId: Int, val sourceDirectId: Int, val sourcePosition: Point?) : ServerPacket {
    constructor(reader: NetworkBuffer) : this(
        reader.readVarInt(),
        reader.readVarInt(),
        reader.readOptionalEntityId(),
        reader.readOptionalEntityId(),
        reader.readNullable { Vec(it.readDouble(), it.readDouble(), it.readDouble()) },
    )

    override fun write(writer: NetworkBuffer) {
        writer.writeVarInt(entityId)
        writer.writeVarInt(sourceTypeId)
        writer.writeOptionalEntityId(sourceCauseId)
        writer.writeOptionalEntityId(sourceDirectId)
        writer.writeNullable(sourcePosition) { w, pos ->
            w.writeDouble(pos.x)
            w.writeDouble(pos.y)
            w.writeDouble(pos.z)
        }
    }

    override fun getId(): Int {
        return ServerPacketIdentifier.DAMAGE_EVENT
    }

    companion object {
        private fun NetworkBuffer.writeOptionalEntityId(entityId: Int) {
            writeVarInt(entityId + 1)
        }

        private fun NetworkBuffer.readOptionalEntityId(): Int {
            return readVarInt() - 1
        }
    }
}
