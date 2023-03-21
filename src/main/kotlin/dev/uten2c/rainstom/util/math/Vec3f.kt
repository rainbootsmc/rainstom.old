package dev.uten2c.rainstom.util.math

import net.minestom.server.coordinate.Vec
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.math.sqrt

data class Vec3f(val x: Float, val y: Float, val z: Float) {
    constructor(d: Float) : this(d, d, d)

    fun x(): Float = x
    fun y(): Float = y
    fun z(): Float = z

    fun toVec(): Vec {
        return Vec(x.toDouble(), y.toDouble(), z.toDouble())
    }

    fun withX(x: Float): Vec3f {
        return this.copy(x = x)
    }

    fun withX(x: (currentX: Float) -> Float): Vec3f {
        contract {
            callsInPlace(x, InvocationKind.EXACTLY_ONCE)
        }
        return this.copy(x = x(this.x))
    }

    fun withY(y: Float): Vec3f {
        return this.copy(y = y)
    }

    fun withY(y: (currentY: Float) -> Float): Vec3f {
        contract {
            callsInPlace(y, InvocationKind.EXACTLY_ONCE)
        }
        return this.copy(y = y(this.y))
    }

    fun withZ(z: Float): Vec3f {
        return this.copy(z = z)
    }

    fun withZ(z: (currentZ: Float) -> Float): Vec3f {
        contract {
            callsInPlace(z, InvocationKind.EXACTLY_ONCE)
        }
        return this.copy(z = z(this.z))
    }

    fun addX(dx: Float): Vec3f {
        return this.copy(x = x + dx)
    }

    fun addY(dy: Float): Vec3f {
        return this.copy(y = y + dy)
    }

    fun addZ(dz: Float): Vec3f {
        return this.copy(z = z + dz)
    }

    fun add(other: Vec3f): Vec3f {
        return Vec3f(x + other.x, y + other.y, z + other.z)
    }

    fun sub(other: Vec3f): Vec3f {
        return Vec3f(x - other.x, y - other.y, z - other.z)
    }

    fun mul(scale: Float): Vec3f {
        return Vec3f(x * scale, y * scale, z * scale)
    }

    fun div(scale: Float): Vec3f {
        return Vec3f(x / scale, y / scale, z / scale)
    }

    fun neg(): Vec3f {
        return Vec3f(-x, -y, -z)
    }

    fun abs(): Vec3f {
        return Vec3f(kotlin.math.abs(x), kotlin.math.abs(y), kotlin.math.abs(z))
    }

    fun normalize(): Vec3f {
        return Vec3f(x / length, y / length, z / length)
    }

    val isNormalized: Boolean
        get() = lengthSquared == 1f

    val length: Float
        get() = sqrt(lengthSquared)

    val lengthSquared: Float
        get() = x * x + y * y + z * z

    companion object {
        @JvmField
        val ZERO = Vec3f(0f)

        @JvmField
        val ONE = Vec3f(1f)

        @JvmStatic
        fun fromVec(vec: Vec): Vec3f {
            return Vec3f(vec.x.toFloat(), vec.y.toFloat(), vec.z.toFloat())
        }
    }
}
