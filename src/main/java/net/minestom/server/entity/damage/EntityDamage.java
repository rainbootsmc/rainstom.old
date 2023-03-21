package net.minestom.server.entity.damage;

import dev.uten2c.rainstom.damage.DamageEffects;
import net.minestom.server.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * Represents damage inflicted by an {@link Entity}.
 */
public class EntityDamage extends DamageType {

    private final Entity source;

    public EntityDamage(@NotNull Entity source) {
        super("entity_source", DamageEffects.HURT); // Rainstom DamageEffectsを追加
        this.source = source;
    }

    /**
     * Gets the source of the damage.
     *
     * @return the source
     */
    @NotNull
    public Entity getSource() {
        return source;
    }
}
