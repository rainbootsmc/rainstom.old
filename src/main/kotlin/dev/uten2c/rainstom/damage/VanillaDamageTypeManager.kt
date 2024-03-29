package dev.uten2c.rainstom.damage

import dev.uten2c.rainstom.RAINSTOM
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minestom.server.utils.NamespaceID
import org.jglrxavpok.hephaistos.nbt.NBT
import org.jglrxavpok.hephaistos.nbt.NBTCompound
import org.jglrxavpok.hephaistos.nbt.NBTType

class VanillaDamageTypeManager {
    private val damageTypes = Int2ObjectOpenHashMap<VanillaDamageType>()

    init {
        VanillaDamageTypes.VALUES.forEach(::addVanillaDamageType)
        DamageEffects.entries.forEach {
            val damageType = VanillaDamageType.builder()
                .name(NamespaceID.from(RAINSTOM, it.serializedName))
                .build()
            addVanillaDamageType(damageType)
        }
    }

    fun addVanillaDamageType(damageType: VanillaDamageType) {
        damageTypes[damageType.id] = damageType
    }

    fun removeVanillaDamageType(damageType: VanillaDamageType) {
        damageTypes.remove(damageType.id)
    }

    fun getById(id: Int): VanillaDamageType? {
        return damageTypes.get(id)
    }

    fun getByName(namespaceID: NamespaceID): VanillaDamageType? {
        return damageTypes.values.firstOrNull { it.name == namespaceID }
    }

    fun toNBT(): NBTCompound {
        return NBT.Kompound {
            setString("type", "minecraft:damage_type")
            set("value", NBT.List(NBTType.TAG_Compound, damageTypes.values.map(VanillaDamageType::toNBT)))
        }
    }
}
