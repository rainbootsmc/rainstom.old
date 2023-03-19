package dev.uten2c.wagasa.util.network

import dev.uten2c.wagasa.util.math.Quaternionf
import dev.uten2c.wagasa.util.math.Vec3f
import dev.uten2c.wagasa.util.point.ChunkPos
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.minestom.server.coordinate.Point
import net.minestom.server.item.ItemStack
import net.minestom.server.network.NetworkBuffer
import net.minestom.server.particle.Particle
import net.minestom.server.utils.Direction
import org.jglrxavpok.hephaistos.nbt.NBT
import java.util.*

fun NetworkBuffer.readBoolean(): Boolean {
    return read(NetworkBuffer.BOOLEAN)
}

fun NetworkBuffer.writeBoolean(value: Boolean) {
    write(NetworkBuffer.BOOLEAN, value)
}

fun NetworkBuffer.readByte(): Byte {
    return read(NetworkBuffer.BYTE)
}

fun NetworkBuffer.writeByte(value: Byte) {
    write(NetworkBuffer.BYTE, value)
}

fun NetworkBuffer.readShort(): Short {
    return read(NetworkBuffer.SHORT)
}

fun NetworkBuffer.writeShort(value: Short) {
    write(NetworkBuffer.SHORT, value)
}

fun NetworkBuffer.readUShort(): Int {
    return read(NetworkBuffer.UNSIGNED_SHORT)
}

fun NetworkBuffer.writeUShort(value: Int) {
    write(NetworkBuffer.UNSIGNED_SHORT, value)
}

fun NetworkBuffer.writeUShort(value: UShort) {
    write(NetworkBuffer.UNSIGNED_SHORT, value.toInt())
}

fun NetworkBuffer.readInt(): Int {
    return read(NetworkBuffer.INT)
}

fun NetworkBuffer.writeInt(value: Int) {
    write(NetworkBuffer.INT, value)
}

fun NetworkBuffer.readLong(): Long {
    return read(NetworkBuffer.LONG)
}

fun NetworkBuffer.writeLong(value: Long) {
    write(NetworkBuffer.LONG, value)
}

fun NetworkBuffer.readFloat(): Float {
    return read(NetworkBuffer.FLOAT)
}

fun NetworkBuffer.writeFloat(value: Float) {
    write(NetworkBuffer.FLOAT, value)
}

fun NetworkBuffer.readDouble(): Double {
    return read(NetworkBuffer.DOUBLE)
}

fun NetworkBuffer.writeDouble(value: Double) {
    write(NetworkBuffer.DOUBLE, value)
}

fun NetworkBuffer.readVarInt(): Int {
    return read(NetworkBuffer.VAR_INT)
}

fun NetworkBuffer.writeVarInt(value: Int) {
    write(NetworkBuffer.VAR_INT, value)
}

fun NetworkBuffer.readVarLong(): Long {
    return read(NetworkBuffer.VAR_LONG)
}

fun NetworkBuffer.writeVarLong(value: Long) {
    write(NetworkBuffer.VAR_LONG, value)
}

fun NetworkBuffer.readRawBytes(): ByteArray {
    return read(NetworkBuffer.RAW_BYTES)
}

fun NetworkBuffer.writeRawBytes(value: ByteArray) {
    write(NetworkBuffer.RAW_BYTES, value)
}

fun NetworkBuffer.readString(): String {
    return read(NetworkBuffer.STRING)
}

fun NetworkBuffer.writeString(value: String) {
    write(NetworkBuffer.STRING, value)
}

fun NetworkBuffer.readNbt(): NBT {
    return read(NetworkBuffer.NBT)
}

fun NetworkBuffer.writeNbt(value: NBT) {
    write(NetworkBuffer.NBT, value)
}

fun NetworkBuffer.readBlockPos(): Point {
    return read(NetworkBuffer.BLOCK_POSITION)
}

fun NetworkBuffer.writeBlockPos(value: Point) {
    write(NetworkBuffer.BLOCK_POSITION, value)
}

fun NetworkBuffer.readComponent(): Component {
    return read(NetworkBuffer.COMPONENT)
}

fun NetworkBuffer.writeComponent(value: Component) {
    write(NetworkBuffer.COMPONENT, value)
}

fun NetworkBuffer.readUuid(): UUID {
    return read(NetworkBuffer.UUID)
}

fun NetworkBuffer.writeUuid(value: UUID) {
    write(NetworkBuffer.UUID, value)
}

fun NetworkBuffer.readItemStack(): ItemStack {
    return read(NetworkBuffer.ITEM)
}

fun NetworkBuffer.writeItemStack(value: ItemStack) {
    write(NetworkBuffer.ITEM, value)
}

fun NetworkBuffer.readByteArray(): ByteArray {
    return read(NetworkBuffer.BYTE_ARRAY)
}

fun NetworkBuffer.writeByteArray(value: ByteArray) {
    write(NetworkBuffer.BYTE_ARRAY, value)
}

fun NetworkBuffer.readLongArray(): LongArray {
    return read(NetworkBuffer.LONG_ARRAY)
}

fun NetworkBuffer.writeLongArray(value: LongArray) {
    write(NetworkBuffer.LONG_ARRAY, value)
}

fun NetworkBuffer.readVarIntArray(): IntArray {
    return read(NetworkBuffer.VAR_INT_ARRAY)
}

fun NetworkBuffer.writeVarIntArray(value: IntArray) {
    write(NetworkBuffer.VAR_INT_ARRAY, value)
}

fun NetworkBuffer.readVarLongArray(): LongArray {
    return read(NetworkBuffer.VAR_LONG_ARRAY)
}

fun NetworkBuffer.writeVarLongArray(value: LongArray) {
    write(NetworkBuffer.VAR_LONG_ARRAY, value)
}

fun NetworkBuffer.readRotation(): Point {
    return read(NetworkBuffer.ROTATION)
}

fun NetworkBuffer.writeRotation(value: Point) {
    write(NetworkBuffer.ROTATION, value)
}

fun NetworkBuffer.readDirection(): Direction {
    return read(NetworkBuffer.DIRECTION)
}

fun NetworkBuffer.writeDirection(value: Direction) {
    write(NetworkBuffer.DIRECTION, value)
}

inline fun <reified T : Enum<T>> NetworkBuffer.readEnum(): T {
    return readEnum(T::class.java)
}

fun <E : Enum<*>> NetworkBuffer.writeEnum(value: E) {
    writeVarInt(value.ordinal)
}

fun NetworkBuffer.readKey(): Key {
    return Key.key(read(NetworkBuffer.STRING))
}

fun NetworkBuffer.writeKey(value: Key) {
    write(NetworkBuffer.STRING, value.asString())
}

fun <T : Any> NetworkBuffer.readNullable(reader: (NetworkBuffer) -> T?): T? {
    return if (readBoolean()) reader(this) else null
}

fun <T : Any> NetworkBuffer.writeNullable(value: T?, writer: (NetworkBuffer, T) -> Unit) {
    if (value == null) {
        writeBoolean(false)
        return
    }
    writeBoolean(true)
    writer(this, value)
}

fun NetworkBuffer.writeParticle(particle: Particle, dataWriter: ((NetworkBuffer) -> Unit)? = null) {
    writeVarInt(particle.id())
    dataWriter?.invoke(this)
}

fun NetworkBuffer.readChunkPos(): ChunkPos {
    return ChunkPos(readLong())
}

fun NetworkBuffer.writeChunkPos(chunkPos: ChunkPos) {
    writeLong(chunkPos.toLong())
}

fun NetworkBuffer.readVec3f(): Vec3f {
    return Vec3f(readFloat(), readFloat(), readFloat())
}

fun NetworkBuffer.writeVec3f(vec3f: Vec3f) {
    writeFloat(vec3f.x)
    writeFloat(vec3f.y)
    writeFloat(vec3f.z)
}

fun NetworkBuffer.readQuaternionf(): Quaternionf {
    return Quaternionf(readFloat(), readFloat(), readFloat(), readFloat())
}

fun NetworkBuffer.writeQuaternionf(quaternionf: Quaternionf) {
    writeFloat(quaternionf.x)
    writeFloat(quaternionf.y)
    writeFloat(quaternionf.z)
    writeFloat(quaternionf.w)
}
