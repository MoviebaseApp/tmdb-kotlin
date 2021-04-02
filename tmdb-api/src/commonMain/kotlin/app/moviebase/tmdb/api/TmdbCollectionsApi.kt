package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbBelongsToCollection
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbCollectionsApi(private val client: HttpClient) {

    suspend fun getDetails(collectionId: Int, language: String? = null): TmdbBelongsToCollection = client.get {
        endPointV3("collection", collectionId.toString())
        parameterLanguage(language)
    }

}
