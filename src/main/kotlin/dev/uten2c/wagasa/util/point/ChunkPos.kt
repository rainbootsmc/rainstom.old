package dev.uten2c.wagasa.util.point

class ChunkPos(val x: Int, val z: Int) {
    constructor(long: Long) : this(long.toInt(), (long shr 32).toInt())

    fun toLong(): Long {
        return x.toLong() and 0xFFFFFFFFL or (z.toLong() and 0xFFFFFFFFL shl 32)
    }
}
