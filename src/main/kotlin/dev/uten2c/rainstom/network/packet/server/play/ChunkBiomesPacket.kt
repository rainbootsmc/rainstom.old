package dev.uten2c.rainstom.network.packet.server.play

import dev.uten2c.rainstom.util.point.ChunkPos
import net.minestom.server.network.NetworkBuffer
import net.minestom.server.network.packet.server.ServerPacket
import net.minestom.server.network.packet.server.ServerPacketIdentifier

data class ChunkBiomesPacket(val chunkBiomeData: List<ChunkBiomeData>) : ServerPacket {
    constructor(reader: NetworkBuffer) : this(reader.readCollection { r ->
        ChunkBiomeData(ChunkPos(r.read(NetworkBuffer.LONG)), r.read(NetworkBuffer.BYTE_ARRAY))
    })

    override fun write(writer: NetworkBuffer) {
        writer.writeCollection(chunkBiomeData) { w, data ->
            w.write(NetworkBuffer.LONG, data.chunkPos.toLong())
            w.write(NetworkBuffer.BYTE_ARRAY, data.buffer)
        }
    }

    override fun getId(): Int {
        return ServerPacketIdentifier.CHUNK_BIOMES
    }

    data class ChunkBiomeData(val chunkPos: ChunkPos, val buffer: ByteArray) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ChunkBiomeData

            if (chunkPos != other.chunkPos) return false
            return buffer.contentEquals(other.buffer)
        }

        override fun hashCode(): Int {
            var result = chunkPos.hashCode()
            result = 31 * result + buffer.contentHashCode()
            return result
        }
    }
}
