package dev.uten2c.wagasa.util.math

data class Quaternionf(val x: Float, val y: Float, val z: Float, val w: Float) {
    constructor(vec3f: Vec3f, w: Float) : this(vec3f.x, vec3f.y, vec3f.z, w)

    fun x(): Float = x
    fun y(): Float = y
    fun z(): Float = z
    fun w(): Float = w
}
