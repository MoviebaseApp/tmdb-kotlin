package app.moviebase.tmdb

import app.moviebase.tmdb.api.*
import app.moviebase.tmdb.remote.TmdbHttpClientFactory
import app.moviebase.tmdb.remote.TmdbLogLevel
import app.moviebase.tmdb.remote.TmdbSessionProvider
import io.ktor.client.*

class Tmdb3(
    tmdbApiKey: String,
    logLevel: TmdbLogLevel = TmdbLogLevel.NONE,
    tmdbSessionProvider: TmdbSessionProvider? = null,
    httpClientConfigBlock: (HttpClientConfig<*>.() -> Unit)? = null,
) {
    private val client: HttpClient = TmdbHttpClientFactory.create(
        tmdbApiKey = tmdbApiKey,
        logLevel = logLevel,
        httpClientConfigBlock = httpClientConfigBlock,
    )
    private val clientWithSession: HttpClient = TmdbHttpClientFactory.createWithSession(
        tmdbApiKey = tmdbApiKey,
        logLevel = logLevel,
        tmdbSessionProvider = tmdbSessionProvider,
        httpClientConfigBlock = httpClientConfigBlock,
    )

    val account = TmdbAccountApi(clientWithSession)
    val authentication = TmdbAuthenticationApi(client)
    val certifications = TmdbCertificationsApi(client)
    val changes = TmdbChangesApi(client)
    val collections = TmdbCollectionsApi(client)
    val companies = TmdbCompaniesApi(client)
    val configuration = TmdbConfigurationApi(client)
    val credits = TmdbCreditsApi(client)
    val discover = TmdbDiscoverApi(client)
    val find = TmdbFindApi(client)
    val genres = TmdbGenresApi(client)
    val guestSessions = TmdbGuestSessionsApi(client)
    val keywords = TmdbKeywordsApi(client)
    val lists = TmdbListsApi(client)
    val movies = TmdbMoviesApi(client)
    val networks = TmdbNetworksApi(client)
    val trending = TmdbTrendingApi(client)
    val people = TmdbPeopleApi(client)
    val reviews = TmdbReviewsApi(client)
    val search = TmdbSearchApi(client)
    val show = TmdbShowApi(client)
    val showSeasons = TmdbShowSeasonsApi(client)
    val showEpisodes = TmdbShowEpisodesApi(client)
    val showEpisodeGroups = TmdbShowEpisodeGroupsApi(client)

}

