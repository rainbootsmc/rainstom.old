package dev.uten2c.rainstom.inventory

import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.item.ItemStack

/**
 * [Inventory]に実装することでいろいろなイベントをリッスンできるようになる
 */
interface InventoryListener {
    /**
     * プレイヤーがインベントリーを開いたときに発火する
     */
    fun onOpen(player: Player) {}

    /**
     * プレイヤーがインベントリーを閉じると発火する。また、開いているときに切断などをしても発火する。
     */
    fun onClose(player: Player) {}

    /**
     * プレイヤーからボタンを押したパケットが送られてくると発火する
     */
    fun onButtonClick(player: Player, button: Int) {}

    /**
     * アイテムが入れ替わると発火する
     */
    fun onItemChange(slot: Int, previousItem: ItemStack, newItem: ItemStack) {}
}
