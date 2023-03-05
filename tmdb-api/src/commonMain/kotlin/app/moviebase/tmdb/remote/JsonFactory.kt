package app.moviebase.tmdb.remote

import app.moviebase.tmdb.model.TmdbMediaListItem
import app.moviebase.tmdb.model.TmdbMovie
import app.moviebase.tmdb.model.TmdbShow
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

object JsonFactory {

    fun buildJson(): Json = Json {
        encodeDefaults = false
        ignoreUnknownKeys = true
        isLenient = true
        allowSpecialFloatingPointValues = true
        prettyPrint = false

        val module = SerializersModule {
            polymorphic(TmdbMediaListItem::class, TmdbShow::class, TmdbShow.serializer())
            polymorphic(TmdbMediaListItem::class, TmdbMovie::class, TmdbMovie.serializer())

//            contextual(LocalDateTime::class, LocalDateTimeIso8601Serializer)
//            contextual(LocalDate::class, LocalDateIso8601Serializer)
        }
        serializersModule = module
        classDiscriminator = "media_type"
    }
}
