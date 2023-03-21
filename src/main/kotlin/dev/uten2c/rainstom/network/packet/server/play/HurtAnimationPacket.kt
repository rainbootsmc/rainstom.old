package dev.uten2c.rainstom.network.packet.server.play

import dev.uten2c.rainstom.util.network.readFloat
import dev.uten2c.rainstom.util.network.readVarInt
import dev.uten2c.rainstom.util.network.writeFloat
import dev.uten2c.rainstom.util.network.writeVarInt
import net.minestom.server.network.NetworkBuffer
import net.minestom.server.network.packet.server.ServerPacket
import net.minestom.server.network.packet.server.ServerPacketIdentifier

data class HurtAnimationPacket(val entityId: Int, val yaw: Float) : ServerPacket {
    constructor(reader: NetworkBuffer) : this(reader.readVarInt(), reader.readFloat())

    override fun write(writer: NetworkBuffer) {
        writer.writeVarInt(entityId)
        writer.writeFloat(yaw)
    }

    override fun getId(): Int {
        return ServerPacketIdentifier.HURT_ANIMATION
    }
}
