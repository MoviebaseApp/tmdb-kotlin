package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbCompanyDetail
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbCompaniesApi(private val client: HttpClient) {

    suspend fun getDetails(companyId: Int): TmdbCompanyDetail = client.get {
        endPointV3("company", companyId.toString())
    }

}
