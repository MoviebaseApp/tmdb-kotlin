package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbCompanyDetail
import app.moviebase.tmdb.remote.endPointV3
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbCompaniesApi internal constructor(private val client: HttpClient) {

    suspend fun getDetails(companyId: Int): TmdbCompanyDetail = client.get {
        endPointV3("company", companyId.toString())
    }.body()
}
