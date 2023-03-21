package dev.uten2c.rainstom.event.inventory

import net.minestom.server.entity.Player
import net.minestom.server.event.trait.InventoryEvent
import net.minestom.server.event.trait.PlayerInstanceEvent
import net.minestom.server.inventory.Inventory

data class InventoryButtonClickEvent(
    private val player: Player,
    private val inventory: Inventory?,
    val button: Int,
) : InventoryEvent, PlayerInstanceEvent {
    override fun getInventory(): Inventory? {
        return inventory
    }

    override fun getPlayer(): Player {
        return player
    }
}
