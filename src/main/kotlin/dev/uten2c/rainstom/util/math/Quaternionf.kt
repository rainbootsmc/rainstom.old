package dev.uten2c.rainstom.util.math

@JvmRecord
data class Quaternionf(val x: Float, val y: Float, val z: Float, val w: Float) {
    constructor(vec3f: Vec3f, w: Float) : this(vec3f.x, vec3f.y, vec3f.z, w)
}
