package dev.uten2c.rainstom.util.point

import net.minestom.server.coordinate.Pos

/**
 * [Pos]のコンストラクターのシンタックスシュガー
 */
fun pos(x: Number, y: Number, z: Number): Pos =
    Pos(x.toDouble(), y.toDouble(), z.toDouble())

/**
 * [Pos]のコンストラクターのシンタックスシュガー
 */
fun pos(x: Number, y: Number, z: Number, yaw: Number, pitch: Number): Pos =
    Pos(x.toDouble(), y.toDouble(), z.toDouble(), yaw.toFloat(), pitch.toFloat())

fun Pos.addX(dx: Double): Pos {
    return withX { it + dx }
}

fun Pos.addY(dy: Double): Pos {
    return withY { it + dy }
}

fun Pos.addZ(dz: Double): Pos {
    return withZ { it + dz }
}
