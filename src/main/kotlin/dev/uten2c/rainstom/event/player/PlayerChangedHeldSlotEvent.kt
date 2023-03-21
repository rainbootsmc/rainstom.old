package dev.uten2c.rainstom.event.player

import net.minestom.server.entity.Player
import net.minestom.server.event.trait.PlayerInstanceEvent

data class PlayerChangedHeldSlotEvent(private val player: Player, val previousSlot: Int, val newSlot: Int) : PlayerInstanceEvent {
    override fun getPlayer(): Player {
        return player
    }
}
