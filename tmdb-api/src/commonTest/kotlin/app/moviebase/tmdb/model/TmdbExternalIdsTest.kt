package app.moviebase.tmdb.model

import app.moviebase.tmdb.core.JsonFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TmdbExternalIdsTest {

    private val json = JsonFactory.buildJson()

    @Test
    fun `it decodes regular external ids`() {
        val payload = """
            {
              "imdb_id": "tt0944947",
              "freebase_mid": null,
              "freebase_id": null,
              "tvdb_id": 121361,
              "tvrage_id": 24493,
              "wikidata_id": "Q23572",
              "facebook_id": "GameOfThrones",
              "instagram_id": "gameofthrones",
              "twitter_id": "GameOfThrones"
            }
        """.trimIndent()

        val externalIds = json.decodeFromString(TmdbExternalIds.serializer(), payload)

        assertEquals(121361, externalIds.tvdbId)
        assertEquals(24493, externalIds.tvrageId)
        assertEquals("tt0944947", externalIds.imdbId)
    }

    @Test
    fun `it decodes an out of range tvdb id as null`() {
        val payload = """
            {
              "imdb_id": "tt1234567",
              "freebase_mid": null,
              "freebase_id": null,
              "tvdb_id": 12835200512,
              "tvrage_id": null,
              "wikidata_id": "Q42",
              "facebook_id": null,
              "instagram_id": null,
              "twitter_id": null
            }
        """.trimIndent()

        val externalIds = json.decodeFromString(TmdbExternalIds.serializer(), payload)

        assertNull(externalIds.tvdbId)
        assertEquals("tt1234567", externalIds.imdbId)
        assertEquals("Q42", externalIds.wikidata)
    }

    @Test
    fun `it decodes an out of range tvrage id as null`() {
        val payload = """
            {
              "tvdb_id": 121361,
              "tvrage_id": 98765432109
            }
        """.trimIndent()

        val externalIds = json.decodeFromString(TmdbExternalIds.serializer(), payload)

        assertEquals(121361, externalIds.tvdbId)
        assertNull(externalIds.tvrageId)
    }

    @Test
    fun `it decodes a non numeric tvdb id as null`() {
        val payload = """
            {
              "tvdb_id": "not-a-number",
              "tvrage_id": ""
            }
        """.trimIndent()

        val externalIds = json.decodeFromString(TmdbExternalIds.serializer(), payload)

        assertNull(externalIds.tvdbId)
        assertNull(externalIds.tvrageId)
    }

    @Test
    fun `it decodes a show detail with an out of range tvdb id`() {
        val payload = """
            {
              "id": 1399,
              "name": "Game of Thrones",
              "external_ids": {
                "imdb_id": "tt0944947",
                "tvdb_id": 12835200512,
                "tvrage_id": null,
                "wikidata_id": "Q23572"
              }
            }
        """.trimIndent()

        val showDetail = json.decodeFromString(TmdbShowDetail.serializer(), payload)

        assertEquals("Game of Thrones", showDetail.name)
        assertNull(showDetail.externalIds?.tvdbId)
        assertEquals("tt0944947", showDetail.externalIds?.imdbId)
    }

    @Test
    fun `it encodes a tvdb id back to a number`() {
        val externalIds = TmdbExternalIds(imdbId = "tt0944947", tvdbId = 121361)

        val encoded = json.encodeToString(TmdbExternalIds.serializer(), externalIds)

        assertEquals("""{"imdb_id":"tt0944947","tvdb_id":121361}""", encoded)
    }
}
