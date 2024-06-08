package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbConfiguration
import app.moviebase.tmdb.core.endPointV3
import app.moviebase.tmdb.model.TmdbConfigurationCountry
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbConfigurationApi internal constructor(private val client: HttpClient) {

    /**
     * Get the system wide configuration information.
     * @see [Documentation] (https://developers.themoviedb.org/3/configuration/get-api-configuration)
     */
    suspend fun getApiConfiguration(): TmdbConfiguration = client.get {
        endPointV3("configuration")
    }.body()

    /**
     * Get the list of countries (ISO 3166-1 tags) used throughout TMDB.
     * [Documentation](https://developer.themoviedb.org/reference/configuration-countries)
     */
    suspend fun getCountries(): List<TmdbConfigurationCountry> = client.get {
        endPointV3("configuration", "countries")
    }.body()
}
