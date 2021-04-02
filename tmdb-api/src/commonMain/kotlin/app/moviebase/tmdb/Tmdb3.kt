package app.moviebase.tmdb

import app.moviebase.tmdb.api.*
import app.moviebase.tmdb.remote.buildHttpClient
import io.ktor.client.request.*

class Tmdb3(tmdbApiKey: String) {

    private val client = buildHttpClient {
        it.header(TmdbUrlParameter.API_KEY, tmdbApiKey)
    }

    val account = TmdbAccountApi(client)
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
