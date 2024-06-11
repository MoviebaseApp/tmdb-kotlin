package app.moviebase.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object TmdbNetworkId {
    const val NETFLIX = 213
    const val HBO = 49
    const val AMC = 174
    const val AMAZON = 1024
    const val APPLE_TV = 2552
    const val DISNEY_PLUS = 2739
    const val FOX = 19
    const val FX = 88
    const val ABC = 2
    const val NBC = 6
    const val CBS = 16
    const val HISTORY = 65
    const val THE_CW = 71
    const val PARAMOUNT = 4330

    const val ESPN = 29
    const val LIFETIME = 34
    const val SHOWTIME = 67
    const val VH1 = 158
    const val BRAVO = 74
    const val COMEDY_CENTRAL = 47
    const val NATIONAL_GEOGRAPHIC = 43
    const val BBC_AMERICA = 493
    const val FUJI_TV = 2
    const val NICKELODEON = 13
    const val PBS = 14
    const val A_AND_E = 129
    const val ANIMAL_PLANET = 91
    const val ADULT_SWIM = 80
    const val TNT = 41
    const val HULU = 453
    const val YOUTUBE_PREMIUM = 1436
    const val SYFY = 77
    const val USA = 30
}

@Serializable
data class TmdbNetwork(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String? = null,
    @SerialName("origin_country") val originCountry: String? = null,
    @SerialName("headquarters") val headquarters: String? = null,
    @SerialName("homepage") val homepage: String? = null,
    @SerialName("images") val images: NetworkImages? = null,
    @SerialName("logo_path") val logoPath: String? = null
)

@Serializable
data class NetworkImages(
    @SerialName("logos") val logos: List<TmdbLogoImage> = emptyList()
)
