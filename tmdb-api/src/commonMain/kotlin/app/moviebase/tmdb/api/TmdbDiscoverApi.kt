package app.moviebase.tmdb.api

import app.moviebase.tmdb.discover.DiscoverCategory
import app.moviebase.tmdb.discover.DiscoverFactory
import app.moviebase.tmdb.model.*
import io.ktor.client.*
import io.ktor.client.request.*

class TmdbDiscoverApi(private val client: HttpClient) {

    suspend fun discoverByCategory(
        page: Int,
        language: String? = null,
        region: String? = null,
        category: DiscoverCategory
    ): TmdbPageResult<out TmdbMediaListItem> {
        return when (val discover = DiscoverFactory.createByCategory(category)) {
            is TmdbDiscover.Movie -> discoverMovie(
                page = page,
                language = language,
                region = region,
                discover = discover
            )
            is TmdbDiscover.Show -> discoverShow(
                page = page,
                language = language,
                region = region,
                discover = discover
            )
        }
    }

    suspend fun discoverMovie(
        page: Int,
        language: String? = null,
        region: String? = null,
        discover: TmdbDiscover.Movie
    ): TmdbMoviePageResult = discoverMovie(
        page = page,
        language = language,
        region = region,
        parameters = discover.buildParameters()
    )

    suspend fun discoverMovie(
        page: Int,
        language: String? = null,
        region: String? = null,
        parameters: Map<String, Any?>
    ): TmdbMoviePageResult = client.get {
        endPointV3("discover", "movie")
        parameterPage(page)
        parameterLanguage(language)
        parameterRegion(region)
        parameters(parameters)
    }

    suspend fun discoverShow(
        page: Int,
        language: String? = null,
        region: String? = null,
        discover: TmdbDiscover.Show
    ): TmdbShowPageResult = discoverShow(
        page = page,
        language = language,
        region = region,
        parameters = discover.buildParameters()
    )

    suspend fun discoverShow(
        page: Int,
        language: String? = null,
        region: String? = null,
        parameters: Map<String, Any?>
    ): TmdbShowPageResult = client.get {
        endPointV3("discover", "tv")
        parameterPage(page)
        parameterLanguage(language)
        parameterRegion(region)
        parameters(parameters)
    }

}
