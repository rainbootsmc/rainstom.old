package dev.uten2c.rainstom.damage

enum class DamageScaling(val serializedName: String) {
    NEVER("never"),
    WHEN_CAUSED_BY_LIVING_NON_PLAYER("when_caused_by_living_non_player"),
    ALWAYS("always"),
    ;

    companion object {
        @JvmStatic
        fun fromSerializedName(serializedName: String): DamageScaling? =
            values().find { it.serializedName == serializedName }
    }
}
