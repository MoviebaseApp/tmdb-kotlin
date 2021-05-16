package app.moviebase.tmdb

import app.moviebase.tmdb.api.*
import app.moviebase.tmdb.remote.TmdbLogLevel
import app.moviebase.tmdb.remote.buildHttpClient
import io.ktor.client.*
import io.ktor.client.request.*

class Tmdb3(
    private val tmdbApiKey: String,
    logLevel: TmdbLogLevel = TmdbLogLevel.NONE
) {

    var sessionId: String? = null

    private val client: HttpClient = buildHttpClient(logLevel) {
        it.parameter(TmdbUrlParameter.API_KEY, tmdbApiKey)
    }

    private val clientWithSession: HttpClient = buildHttpClient(logLevel) {
        it.parameter(TmdbUrlParameter.API_KEY, tmdbApiKey)
        requireNotNull(sessionId) { "session id is not set in Tmdb3 instance" }
        it.parameter(TmdbUrlParameter.SESSION_ID, sessionId)
    }

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
