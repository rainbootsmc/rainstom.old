package dev.uten2c.rainstom.entity.metadata

import net.minestom.server.entity.Metadata
import net.minestom.server.entity.metadata.EntityMeta
import java.util.function.Function
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MetadataValue<T : Metadata.Entry<E>, E>(private val metadata: Metadata, private val id: Int, private val defaultValue: E, private val factory: Function<E, T>) : ReadWriteProperty<EntityMeta, E> {
    override fun getValue(thisRef: EntityMeta, property: KProperty<*>): E {
        return metadata.getIndex(id, defaultValue)
    }

    override fun setValue(thisRef: EntityMeta, property: KProperty<*>, value: E) {
        metadata.setIndex(id, factory.apply(value))
    }
}
