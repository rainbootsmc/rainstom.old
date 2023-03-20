package net.minestom.server.event.item;

import dev.uten2c.wagasa.item.drop.DropAmount;
import dev.uten2c.wagasa.item.drop.DropType;
import net.minestom.server.entity.Player;
import net.minestom.server.event.trait.CancellableEvent;
import net.minestom.server.event.trait.ItemEvent;
import net.minestom.server.event.trait.PlayerInstanceEvent;
import net.minestom.server.item.ItemStack;
import org.jetbrains.annotations.NotNull;

// Wagasa start DropTypeとDropAmountを追加
public class ItemDropEvent implements PlayerInstanceEvent, ItemEvent, CancellableEvent {

    private final Player player;
    private final ItemStack itemStack;
    private final DropType dropType;
    private final DropAmount dropAmount;

    private boolean cancelled;

    public ItemDropEvent(Player player, ItemStack itemStack, DropType dropType, DropAmount dropAmount) {
        this.player = player;
        this.itemStack = itemStack;
        this.dropType = dropType;
        this.dropAmount = dropAmount;
    }

    @Override
    public @NotNull Player getPlayer() {
        return player;
    }

    @NotNull
    public ItemStack getItemStack() {
        return itemStack;
    }

    public DropType getDropType() {
        return dropType;
    }

    public DropAmount getDropAmount() {
        return dropAmount;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

}
// Wagasa end