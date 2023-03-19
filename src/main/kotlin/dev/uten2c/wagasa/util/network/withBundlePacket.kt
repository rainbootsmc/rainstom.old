package dev.uten2c.wagasa.util.network

import dev.uten2c.wagasa.network.packet.server.play.BundleDelimiterPacket
import net.minestom.server.Viewable
import net.minestom.server.entity.Player
import net.minestom.server.network.packet.server.SendablePacket
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun Player.useBundlePacket(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    sendPacket(BundleDelimiterPacket())
    block()
    sendPacket(BundleDelimiterPacket())
}

fun Player.sendBundledPackets(vararg packets: SendablePacket) {
    sendBundledPackets(packets.toList())
}

fun Player.sendBundledPackets(packets: List<SendablePacket>) {
    sendPacket(BundleDelimiterPacket())
    sendPackets(packets)
    sendPacket(BundleDelimiterPacket())
}

fun Viewable.sendBundledPacketToViewers(vararg packets: SendablePacket) {
    sendBundledPacketToViewers(packets.toList())
}

fun Viewable.sendBundledPacketToViewers(packets: List<SendablePacket>) {
    sendPacketToViewers(BundleDelimiterPacket())
    sendPacketsToViewers(packets)
    sendPacketToViewers(BundleDelimiterPacket())
}

fun Viewable.sendBundledPacketToViewersAndSelf(vararg packets: SendablePacket) {
    sendBundledPacketToViewersAndSelf(packets.toList())
}

fun Viewable.sendBundledPacketToViewersAndSelf(packets: List<SendablePacket>) {
    sendPacketToViewersAndSelf(BundleDelimiterPacket())
    packets.forEach(this::sendPacketToViewersAndSelf)
    sendPacketToViewersAndSelf(BundleDelimiterPacket())
}
