package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbCompany
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbCompaniesApi(private val client: HttpClient) {

    suspend fun getDetails(companyId: Int): TmdbCompany = client.get {
        endPointV3("company", companyId.toString())
    }

}
