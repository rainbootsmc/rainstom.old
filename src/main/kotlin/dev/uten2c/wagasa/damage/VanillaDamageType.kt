package dev.uten2c.wagasa.damage

import kotlinx.atomicfu.atomic
import net.minestom.server.utils.NamespaceID
import org.jglrxavpok.hephaistos.nbt.NBT
import org.jglrxavpok.hephaistos.nbt.NBTCompound

class VanillaDamageType private constructor(val name: NamespaceID, val messageId: String, val scaling: DamageScaling, val exhaustion: Float, val effects: DamageEffects, val deathMessageType: DeathMessageType) {
    val id = idCounter.getAndIncrement()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VanillaDamageType) return false

        if (name != other.name) return false
        if (messageId != other.messageId) return false
        if (scaling != other.scaling) return false
        if (exhaustion != other.exhaustion) return false
        if (effects != other.effects) return false
        if (deathMessageType != other.deathMessageType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + messageId.hashCode()
        result = 31 * result + scaling.hashCode()
        result = 31 * result + exhaustion.hashCode()
        result = 31 * result + effects.hashCode()
        result = 31 * result + deathMessageType.hashCode()
        return result
    }

    override fun toString(): String {
        return "DamageType(messageId='$messageId', scaling=$scaling, exhaustion=$exhaustion, effects=$effects, deathMessageType=$deathMessageType)"
    }

    fun toNBT(): NBTCompound {
        return NBT.Kompound {
            setString("name", name.asString())
            setInt("id", id)
            this["element"] = NBT.Kompound {
                setString("message_id", messageId)
                setString("scaling", scaling.serializedName)
                setFloat("exhaustion", exhaustion)
                setString("effects", effects.serializedName)
                setString("death_message_type", deathMessageType.serializedName)
            }
        }
    }

    companion object {
        private val idCounter = atomic(0)

        @JvmStatic
        fun builder(): Builder {
            return Builder()
        }
    }

    class Builder {
        private lateinit var name: NamespaceID
        private var messageId: String = "generic"
        private var scaling: DamageScaling = DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER
        private var exhaustion: Float = 0f
        private var effects: DamageEffects = DamageEffects.HURT
        private var deathMessageType: DeathMessageType = DeathMessageType.DEFAULT

        fun name(name: NamespaceID): Builder {
            this.name = name
            return this
        }

        fun messageId(messageId: String): Builder {
            this.messageId = messageId
            return this
        }

        fun scaling(scaling: DamageScaling): Builder {
            this.scaling = scaling
            return this
        }

        fun exhaustion(exhaustion: Float): Builder {
            this.exhaustion = exhaustion
            return this
        }

        fun effects(effects: DamageEffects): Builder {
            this.effects = effects
            return this
        }

        fun deathMessageType(deathMessageType: DeathMessageType): Builder {
            this.deathMessageType = deathMessageType
            return this
        }

        fun build(): VanillaDamageType {
            return VanillaDamageType(name, messageId, scaling, exhaustion, effects, deathMessageType)
        }
    }
}