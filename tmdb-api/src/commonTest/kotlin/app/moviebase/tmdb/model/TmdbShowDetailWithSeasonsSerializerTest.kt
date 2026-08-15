package app.moviebase.tmdb.model

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

class TmdbShowDetailWithSeasonsSerializerTest {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Test
    fun `it resolves the serializer through the runtime lookup used by content negotiation`() {
        val serializer = json.serializersModule.serializer(typeOf<TmdbShowDetailWithSeasons>())

        val result = json.decodeFromString(serializer, SHOW_WITH_SEASONS) as TmdbShowDetailWithSeasons

        assertEquals(94664, result.show.id)
        assertEquals(setOf(1, 2), result.seasons.keys)
        assertEquals(1, result.seasons.getValue(1).seasonNumber)
        assertEquals(2, result.seasons.getValue(2).seasonNumber)
    }

    private companion object {
        const val SHOW_WITH_SEASONS = """
            {
              "id": 94664,
              "name": "The Last Kingdom",
              "number_of_seasons": 2,
              "seasons": [
                { "id": 134338, "season_number": 1 },
                { "id": 334394, "season_number": 2 }
              ],
              "season/1": { "season_number": 1, "episodes": [] },
              "season/2": { "season_number": 2, "episodes": [] }
            }
        """
    }
}
