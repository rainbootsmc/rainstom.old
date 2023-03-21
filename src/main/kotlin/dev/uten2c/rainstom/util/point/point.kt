package dev.uten2c.rainstom.util.point

import net.minestom.server.coordinate.Point
import net.minestom.server.coordinate.Vec

fun Point.asVec(): Vec {
    return Vec(x, y, z)
}
