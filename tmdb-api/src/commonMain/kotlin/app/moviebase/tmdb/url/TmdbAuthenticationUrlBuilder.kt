package app.moviebase.tmdb.url

import app.moviebase.tmdb.TmdbWebConfig

object TmdbAuthenticationUrlBuilder {

    fun buildAuthorizationUrl(requestToken: String, redirectTo: String) =
        "${TmdbWebConfig.BASE_WEBSITE_URL}/authenticate/$requestToken?redirect_to=$redirectTo"
}
