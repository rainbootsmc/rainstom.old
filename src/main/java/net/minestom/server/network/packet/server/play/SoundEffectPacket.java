package net.minestom.server.network.packet.server.play;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound.Source;
import net.minestom.server.adventure.AdventurePacketConvertor;
import net.minestom.server.coordinate.Point;
import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.ServerPacketIdentifier;
import net.minestom.server.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minestom.server.network.NetworkBuffer.*;

// Wagasa start 1.19.3対応と再生される座標の小数点が丸められるバグを修正
public record SoundEffectPacket(int soundId, @Nullable Key customSoundId, @Nullable Float staticDistanceToTravel,
                                @NotNull Source source,
                                int packetX, int packetY, int packetZ,
                                float volume, float pitch, long seed) implements ServerPacket {
    public SoundEffectPacket(@NotNull NetworkBuffer reader) {
        this(reader.read(VAR_INT), reader.readEnum(Source.class),
                reader.read(INT) / 8.0, reader.read(INT) / 8.0, reader.read(INT) / 8.0,
                reader.read(FLOAT), reader.read(FLOAT), reader.read(LONG));
    }

    public SoundEffectPacket(int soundId, @NotNull Source source,
                             double x, double y, double z,
                             float volume, float pitch, long seed) {
        this(soundId, null, null, source, (int) (x * 8), (int) (y * 8), (int) (z * 8),
                volume, pitch, seed);
    }

    public SoundEffectPacket(@NotNull SoundEvent sound, @NotNull Source source,
                             @NotNull Point position, float volume, float pitch) {
        this(sound.id(), source, position.x(), position.y(), position.z(),
                volume, pitch, 0);
    }

    public SoundEffectPacket(@NotNull Key customSoundId, @Nullable Float staticDistanceToTravel, @NotNull Source source,
                             double x, double y, double z,
                             float volume, float pitch, long seed) {
        this(0, customSoundId, staticDistanceToTravel, source, (int) (x * 8), (int) (y * 8), (int) (z * 8),
                volume, pitch, seed);
    }

    @Override
    public void write(@NotNull NetworkBuffer writer) {
        if (customSoundId != null) {
            writer.write(VAR_INT, 0);
            writer.write(STRING, customSoundId.asString());
            if (staticDistanceToTravel != null) {
                writer.write(BOOLEAN, true);
                writer.write(FLOAT, staticDistanceToTravel);
            } else {
                writer.write(BOOLEAN, false);
            }
        } else {
            writer.write(VAR_INT, soundId + 1);
        }
        writer.write(VAR_INT, AdventurePacketConvertor.getSoundSourceValue(source));
        writer.write(INT, packetX);
        writer.write(INT, packetY);
        writer.write(INT, packetZ);
        writer.write(FLOAT, volume);
        writer.write(FLOAT, pitch);
        writer.write(LONG, seed);
    }

    @Override
    public int getId() {
        return ServerPacketIdentifier.SOUND_EFFECT;
    }
}
// Wagasa end