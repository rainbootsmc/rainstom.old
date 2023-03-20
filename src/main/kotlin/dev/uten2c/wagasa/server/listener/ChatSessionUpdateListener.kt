package dev.uten2c.wagasa.server.listener

import net.minestom.server.entity.Player
import net.minestom.server.network.packet.client.play.ClientChatSessionUpdatePacket

object ChatSessionUpdateListener {
    @Suppress("UNUSED_PARAMETER")
    @JvmStatic
    fun listener(packet: ClientChatSessionUpdatePacket, player: Player) {
        // Empty
    }
}
