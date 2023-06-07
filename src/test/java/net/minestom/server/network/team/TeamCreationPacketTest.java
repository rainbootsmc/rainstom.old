package net.minestom.server.network.team;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.network.packet.server.play.TeamsPacket;
import net.minestom.testing.Env;
import net.minestom.testing.EnvTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnvTest
public class TeamCreationPacketTest {
    @Test
    public void teamCreationPacketTest(Env env) {
        var instance = env.createFlatInstance();
        var connection = env.createConnection();
        connection.connect(instance, new Pos(0, 42, 0)).join();

        var packetTracker = connection.trackIncoming(TeamsPacket.class);
        var team = MinecraftServer.getTeamManager().createTeam("test team");
        var entity = new LivingEntity(EntityType.PIG);
        entity.setTeam(team);
        entity.setInstance(instance, new Pos(0, 42, 0)).join();

        packetTracker.collect().stream().filter(p -> p.action() instanceof TeamsPacket.CreateTeamAction).forEach(System.out::println);
        assertEquals(1, packetTracker.collect().stream().filter(p -> p.action() instanceof TeamsPacket.CreateTeamAction).count());

        entity.teleport(new Pos(100, 42, 0));
        env.tick();
        entity.teleport(new Pos(0, 42, 0));

        assertEquals(1, packetTracker.collect().stream().filter(p -> p.action() instanceof TeamsPacket.CreateTeamAction).count());
    }
}
