package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object TmdbWatchProviderId {
    const val NETFLIX = 8
    const val AMAZON_PRIME_VIDEO = 9
    const val AMAZON_PRIME_VIDEO_2 = 119 // duplicate provider ID available
    const val AMAZON_VIDEO = 10
    const val APPLE_ITUNES = 2
    const val APPLE_TV_PLUS = 350
    const val DISNEY_PLUS = 337
}

@Serializable
enum class TmdbWatchMonetizationType(val value: String) {
    @SerialName("flatrate")
    FLATRATE("flatrate"),

    @SerialName("free")
    FREE("free"),

    @SerialName("ads")
    ADS("ads"),

    @SerialName("rent")
    RENT("rent"),

    @SerialName("buy")
    BUY("buy")
}

@Serializable
data class TmdbProviderResult(
    val id: Int? = null,
    val results: Map<String, TmdbProviders>
)

@Serializable
data class TmdbProviders(
    val link: String,
    val flatrate: List<TmdbProvider> = emptyList(),
    val buy: List<TmdbProvider> = emptyList()
)

@Serializable
data class TmdbProvider(
    @SerialName("display_priority") val displayPriority: Int?,
    @SerialName("logo_path") val logoPath: String,
    @SerialName("provider_id") val providerId: Int,
    @SerialName("provider_name") val providerName: String
)
