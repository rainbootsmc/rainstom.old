package dev.uten2c.rainstom.server.listener

import dev.uten2c.rainstom.event.inventory.InventoryButtonClickEvent
import dev.uten2c.rainstom.inventory.InventoryListener
import net.minestom.server.entity.Player
import net.minestom.server.event.EventDispatcher
import net.minestom.server.network.packet.client.play.ClientClickWindowButtonPacket

object WindowButtonPacketListener {
    @JvmStatic
    fun listener(packet: ClientClickWindowButtonPacket, player: Player) {
        val inventory = player.openInventory
        if (inventory is InventoryListener && inventory.windowId == packet.windowId) {
            EventDispatcher.call(InventoryButtonClickEvent(player, inventory, packet.buttonId.toInt()))
            inventory.onButtonClick(player, packet.buttonId.toInt())
        }
    }
}