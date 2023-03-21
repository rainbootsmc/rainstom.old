package dev.uten2c.rainstom.network.packet.server.play

import net.minestom.server.network.NetworkBuffer
import net.minestom.server.network.packet.server.ServerPacket
import net.minestom.server.network.packet.server.ServerPacketIdentifier

class BundleDelimiterPacket : ServerPacket {
    constructor(reader: NetworkBuffer) : this()

    constructor()

    override fun write(writer: NetworkBuffer) {
    }

    override fun getId(): Int {
        return ServerPacketIdentifier.BUNDLE_DELIMITER
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BundleDelimiterPacket) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
