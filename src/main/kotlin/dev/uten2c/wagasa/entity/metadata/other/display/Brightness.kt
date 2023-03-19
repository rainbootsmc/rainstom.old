package dev.uten2c.wagasa.entity.metadata.other.display

class Brightness private constructor(val block: Int, val sky: Int) {
    companion object {
        @JvmField
        val FULL_BRIGHT = Brightness(15, 15)

        @JvmStatic
        fun of(block: Int, sky: Int) = Brightness(block.coerceIn(0..15), sky.coerceIn(0..15))

        @JvmStatic
        fun unpack(packed: Int): Brightness {
            val block = packed shr 4 and 0xFFFF
            val sky = packed shr 20 and 0xFFFF
            return of(block, sky)
        }
    }

    fun pack(): Int {
        return (block shl 4) or (sky shl 20)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Brightness) return false

        if (block != other.block) return false
        if (sky != other.sky) return false

        return true
    }

    override fun hashCode(): Int {
        var result = block
        result = 31 * result + sky
        return result
    }

    override fun toString(): String {
        return "Brightness(block=$block, sky=$sky)"
    }
}
