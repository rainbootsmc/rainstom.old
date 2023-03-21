package net.minestom.server.network.packet.server.play;

import net.kyori.adventure.text.Component;
import net.minestom.server.crypto.ChatSession;
import net.minestom.server.entity.GameMode;
import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.ServerPacketIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.*;

import static net.minestom.server.network.NetworkBuffer.*;

// Rainstom start recordからclassに変更
public final class PlayerInfoUpdatePacket implements ServerPacket {
    private final @NotNull EnumSet<@NotNull Action> actions;
    private final @NotNull List<@NotNull Entry> entries;

    public PlayerInfoUpdatePacket(@NotNull EnumSet<@NotNull Action> actions, @NotNull List<@NotNull Entry> entries) {
        this.actions = EnumSet.copyOf(actions);
        this.entries = List.copyOf(entries);
    }

    public PlayerInfoUpdatePacket(@NotNull Action action, @NotNull Entry entry) {
        this(EnumSet.of(action), List.of(entry));
    }

    public PlayerInfoUpdatePacket(@NotNull NetworkBuffer reader) {
        this.actions = reader.readEnumSet(Action.class);
        this.entries = reader.readCollection(buf -> {
            final var uuid = buf.read(UUID);
            String username = null;
            List<Property> properties = Collections.emptyList();
            boolean listed = false;
            int latency = 0;
            GameMode gameMode = GameMode.SURVIVAL;
            Component displayName = null;
            ChatSession chatSession = null;
            for (Action action : this.actions) {
                switch (action) {
                    case ADD_PLAYER -> {
                        username = reader.read(STRING);
                        properties = reader.readCollection(Property::new);
                    }
                    case INITIALIZE_CHAT -> {
                        chatSession = reader.readOptional(ChatSession::new);
                    }
                    case UPDATE_GAME_MODE -> {
                        gameMode = reader.readEnum(GameMode.class);
                    }
                    case UPDATE_LISTED -> {
                        listed = reader.read(BOOLEAN);
                    }
                    case UPDATE_LATENCY -> {
                        latency = reader.read(VAR_INT);
                    }
                    case UPDATE_DISPLAY_NAME -> {
                        displayName = reader.readOptional(COMPONENT);
                    }
                }
            }
            return new Entry(uuid, username, properties, listed, latency, gameMode, displayName, chatSession);
        });
    }

    @Override
    public void write(@NotNull NetworkBuffer writer) {
        writer.writeEnumSet(actions, Action.class);
        writer.writeCollection(entries, (buffer, entry) -> {
            buffer.write(UUID, entry.uuid);
            for (Action action : actions) {
                action.writer.write(buffer, entry);
            }
        });
    }

    @Override
    public int getId() {
        return ServerPacketIdentifier.PLAYER_INFO_UPDATE;
    }

    public @NotNull EnumSet<@NotNull Action> actions() {
        return actions;
    }

    public @NotNull List<@NotNull Entry> entries() {
        return entries;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PlayerInfoUpdatePacket) obj;
        return Objects.equals(this.actions, that.actions) &&
                Objects.equals(this.entries, that.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actions, entries);
    }

    @Override
    public String toString() {
        return "PlayerInfoUpdatePacket[" +
                "actions=" + actions + ", " +
                "entries=" + entries + ']';
    }


    public record Entry(UUID uuid, String username, List<Property> properties,
                        boolean listed, int latency, GameMode gameMode,
                        @Nullable Component displayName, @Nullable ChatSession chatSession) {
        public Entry {
            properties = List.copyOf(properties);
        }
    }

    public record Property(@NotNull String name, @NotNull String value,
                           @Nullable String signature) implements Writer {
        public Property(@NotNull String name, @NotNull String value) {
            this(name, value, null);
        }

        public Property(@NotNull NetworkBuffer reader) {
            this(reader.read(STRING), reader.read(STRING),
                    reader.readOptional(STRING));
        }

        @Override
        public void write(@NotNull NetworkBuffer writer) {
            writer.write(STRING, name);
            writer.write(STRING, value);
            writer.writeOptional(STRING, signature);
        }
    }

    public enum Action {
        ADD_PLAYER((writer, entry) -> {
            writer.write(STRING, entry.username);
            writer.writeCollection(entry.properties);
        }),
        INITIALIZE_CHAT((writer, entry) -> writer.writeOptional(entry.chatSession)),
        UPDATE_GAME_MODE((writer, entry) -> writer.write(VAR_INT, entry.gameMode.ordinal())),
        UPDATE_LISTED((writer, entry) -> writer.write(BOOLEAN, entry.listed)),
        UPDATE_LATENCY((writer, entry) -> writer.write(VAR_INT, entry.latency)),
        UPDATE_DISPLAY_NAME((writer, entry) -> writer.writeOptional(COMPONENT, entry.displayName));

        final Writer writer;

        Action(Writer writer) {
            this.writer = writer;
        }

        interface Writer {
            void write(NetworkBuffer writer, Entry entry);
        }
    }
}
// Rainstom end