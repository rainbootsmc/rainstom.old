package dev.uten2c.rainstom.util.network

import dev.uten2c.rainstom.network.packet.server.play.BundleDelimiterPacket
import net.minestom.server.entity.Player
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

fun Player.useBundlePacket(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    sendPacket(BundleDelimiterPacket())
    block()
    sendPacket(BundleDelimiterPacket())
}
