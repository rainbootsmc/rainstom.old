package net.minestom.server.network.packet.server.play;

import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.ServerPacketIdentifier;
import org.jetbrains.annotations.NotNull;

import static net.minestom.server.network.NetworkBuffer.INT;
import static net.minestom.server.network.NetworkBuffer.VAR_INT;

public record EndCombatEventPacket(int duration) implements ServerPacket { // Rainstom 1.20 entityIdを削除
    public EndCombatEventPacket(@NotNull NetworkBuffer reader) {
        this(reader.read(VAR_INT)); // Rainstom 1.20 entityIdを削除
    }

    @Override
    public void write(@NotNull NetworkBuffer writer) {
        writer.write(VAR_INT, duration);
        // writer.write(INT, entityId); // Rainstom 1.20 entityIdを削除
    }

    @Override
    public int getId() {
        return ServerPacketIdentifier.END_COMBAT_EVENT;
    }
}
