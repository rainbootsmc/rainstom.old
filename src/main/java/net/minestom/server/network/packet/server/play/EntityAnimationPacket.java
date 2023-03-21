package net.minestom.server.network.packet.server.play;

import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.ServerPacketIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

import static net.minestom.server.network.NetworkBuffer.BYTE;
import static net.minestom.server.network.NetworkBuffer.VAR_INT;

public record EntityAnimationPacket(int entityId, @NotNull Animation animation) implements ServerPacket {
    public EntityAnimationPacket(@NotNull NetworkBuffer reader) {
        this(reader.read(VAR_INT), Objects.requireNonNull(Animation.getById(reader.read(BYTE)))); // Rainstom 1.19.4
    }

    @Override
    public void write(@NotNull NetworkBuffer writer) {
        writer.write(VAR_INT, entityId);
        writer.write(BYTE, (byte) animation.id);
    }

    @Override
    public int getId() {
        return ServerPacketIdentifier.ENTITY_ANIMATION;
    }

    // Rainstom start 1.19.4 TAKE_DAMAGEがなくなった関係で飛び番が発生したので対応
    public enum Animation {
        SWING_MAIN_ARM(0),
        LEAVE_BED(2),
        SWING_OFF_HAND(3),
        CRITICAL_EFFECT(4),
        MAGICAL_CRITICAL_EFFECT(5);

        public final int id;

        Animation(int id) {
            this.id = id;
        }

        public static @Nullable Animation getById(int id) {
            return Arrays.stream(values()).filter(anim -> anim.id == id).findFirst().orElse(null);
        }
    }
    // Rainstom end
}
