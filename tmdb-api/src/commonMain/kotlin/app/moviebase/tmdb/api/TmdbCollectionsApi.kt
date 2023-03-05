package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbBelongsToCollection
import app.moviebase.tmdb.remote.endPointV3
import app.moviebase.tmdb.remote.parameterLanguage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbCollectionsApi internal constructor(private val client: HttpClient) {

    suspend fun getDetails(collectionId: Int, language: String? = null): TmdbBelongsToCollection = client.get {
        endPointV3("collection", collectionId.toString())
        parameterLanguage(language)
    }.body()
}
