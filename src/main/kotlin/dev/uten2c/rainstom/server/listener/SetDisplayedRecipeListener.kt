package dev.uten2c.rainstom.server.listener

import net.minestom.server.entity.Player
import net.minestom.server.network.packet.client.play.ClientSetDisplayedRecipePacket

object SetDisplayedRecipeListener {
    @Suppress("UNUSED_PARAMETER")
    @JvmStatic
    fun listener(packet: ClientSetDisplayedRecipePacket, player: Player) {
        // Empty
    }
}
