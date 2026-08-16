package app.moviebase.tmdb.core

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

internal class NullableIntSerializer : KSerializer<Int?> {

    private val delegate = JsonPrimitive.serializer().nullable

    override val descriptor: SerialDescriptor get() = delegate.descriptor
    override fun deserialize(decoder: Decoder): Int? = delegate.deserialize(decoder)?.contentOrNull?.toIntOrNull()
    override fun serialize(encoder: Encoder, value: Int?) = delegate.serialize(encoder, value?.let(::JsonPrimitive))
}
