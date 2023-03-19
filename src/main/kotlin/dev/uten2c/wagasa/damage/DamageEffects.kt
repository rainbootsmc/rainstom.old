package dev.uten2c.wagasa.damage

import net.minestom.server.sound.SoundEvent

enum class DamageEffects(val serializedName: String, val playerSound: SoundEvent) {
    HURT("hurt", SoundEvent.ENTITY_PLAYER_HURT),
    THORNS("thorns", SoundEvent.ENCHANT_THORNS_HIT),
    DROWNING("drowning", SoundEvent.ENTITY_PLAYER_HURT_DROWN),
    BURNING("burning", SoundEvent.ENTITY_PLAYER_HURT_ON_FIRE),
    POKING("poking", SoundEvent.ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH),
    FREEZING("freezing", SoundEvent.ENTITY_PLAYER_HURT_FREEZE),
    ;

    companion object {
        @JvmStatic
        fun fromSerializedName(serializedName: String): DamageEffects? =
                values().find { it.serializedName == serializedName }
    }
}
