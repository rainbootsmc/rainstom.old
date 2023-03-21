package dev.uten2c.rainstom.util.point

import net.minestom.server.coordinate.Vec

fun Vec.addX(dx: Double): Vec {
    return withX { it + dx }
}

fun Vec.addY(dy: Double): Vec {
    return withY { it + dy }
}

fun Vec.addZ(dz: Double): Vec {
    return withZ { it + dz }
}
