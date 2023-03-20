package dev.uten2c.wagasa.server.listener

import net.minestom.server.entity.Player
import net.minestom.server.network.packet.client.play.ClientSetRecipeBookStatePacket

object RecipeBookListener {
    @Suppress("UNUSED_PARAMETER")
    @JvmStatic
    fun listener(packet: ClientSetRecipeBookStatePacket, player: Player) {
        // Empty
    }
}