package dev.uten2c.rainstom.util.point

import net.minestom.server.coordinate.Point
import net.minestom.server.coordinate.Vec

fun Point.asVec(): Vec {
    return Vec(x(), y(), z())
}

fun Point.addX(dx: Double): Point {
    return withX { it + dx }
}

fun Point.addY(dy: Double): Point {
    return withY { it + dy }
}

fun Point.addZ(dz: Double): Point {
    return withZ { it + dz }
}
