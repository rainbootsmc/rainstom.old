package dev.uten2c.wagasa.item.drop

import net.minestom.server.entity.Player.Hand
import net.minestom.server.inventory.AbstractInventory

sealed interface DropType {
    class HotBar(val hand: Hand, val slot: Int) : DropType

    open class Inventory(val inventory: AbstractInventory) : DropType

    class InventoryClose(inventory: AbstractInventory) : Inventory(inventory)
}