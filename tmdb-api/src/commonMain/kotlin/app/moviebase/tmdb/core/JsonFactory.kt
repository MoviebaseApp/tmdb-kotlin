package app.moviebase.tmdb.core

import app.moviebase.tmdb.model.TmdbMediaListItem
import app.moviebase.tmdb.model.TmdbMovie
import app.moviebase.tmdb.model.TmdbPerson
import app.moviebase.tmdb.model.TmdbSearchableListItem
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

            polymorphic(TmdbSearchableListItem::class, TmdbShow::class, TmdbShow.serializer())
            polymorphic(TmdbSearchableListItem::class, TmdbMovie::class, TmdbMovie.serializer())
            polymorphic(TmdbSearchableListItem::class, TmdbPerson::class, TmdbPerson.serializer())
        }
        serializersModule = module
        classDiscriminator = "media_type"
    }
}
