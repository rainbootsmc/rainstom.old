package dev.uten2c.rainstom.damage

enum class DeathMessageType(val serializedName: String) {
    DEFAULT("default"),
    FALL_VARIANTS("fall_variants"),
    INTENTIONAL_GAME_DESIGN("intentional_game_design"),
    ;

    companion object {
        @JvmStatic
        fun fromSerializedName(serializedName: String): DeathMessageType? =
                values().find { it.serializedName == serializedName }
    }
}
