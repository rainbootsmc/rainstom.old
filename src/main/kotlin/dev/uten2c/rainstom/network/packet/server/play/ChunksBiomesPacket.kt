package dev.uten2c.rainstom.network.packet.server.play

import dev.uten2c.rainstom.util.network.readByteArray
import dev.uten2c.rainstom.util.network.readChunkPos
import dev.uten2c.rainstom.util.network.writeByteArray
import dev.uten2c.rainstom.util.network.writeChunkPos
import dev.uten2c.rainstom.util.point.ChunkPos
import net.minestom.server.network.NetworkBuffer
import net.minestom.server.network.packet.server.ServerPacket
import net.minestom.server.network.packet.server.ServerPacketIdentifier

data class ChunksBiomesPacket(val chunkBiomeData: List<ChunkBimeData>) : ServerPacket {
    constructor(reader: NetworkBuffer) : this(reader.readCollection { r -> ChunkBimeData(r.readChunkPos(), r.readByteArray()) })

    override fun write(writer: NetworkBuffer) {
        writer.writeCollection(chunkBiomeData) { w, data ->
            w.writeChunkPos(data.chunkPos)
            w.writeByteArray(data.buffer)
        }
    }

    override fun getId(): Int {
        return ServerPacketIdentifier.CHUNKS_BIOMES
    }

    data class ChunkBimeData(val chunkPos: ChunkPos, val buffer: ByteArray) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is ChunkBimeData) return false

            if (chunkPos != other.chunkPos) return false
            if (!buffer.contentEquals(other.buffer)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = chunkPos.hashCode()
            result = 31 * result + buffer.contentHashCode()
            return result
        }
    }
}
