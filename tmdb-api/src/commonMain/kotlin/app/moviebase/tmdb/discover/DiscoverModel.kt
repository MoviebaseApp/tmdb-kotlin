package app.moviebase.tmdb.discover

import app.moviebase.tmdb.model.TmdbMediaType
import app.moviebase.tmdb.model.TmdbNetworkId
import app.moviebase.tmdb.model.TmdbWatchProviderId

sealed class DiscoverCategory {

    object NowPlaying: DiscoverCategory()
    object Upcoming: DiscoverCategory()
    data class Popular(val mediaType: TmdbMediaType): DiscoverCategory()
    data class TopRated(val mediaType: TmdbMediaType): DiscoverCategory()
    object AiringToday: DiscoverCategory()
    object OnTv: DiscoverCategory()
    data class OnDvd(val mediaType: TmdbMediaType): DiscoverCategory()
    data class Network(val network: Int): DiscoverCategory() {
        companion object {
            val NETFLIX = Network(TmdbNetworkId.NETFLIX)
            val AMAZON = Network(TmdbNetworkId.AMAZON)
            val DISNEY_PLUS = Network(TmdbNetworkId.DISNEY_PLUS)
            val APPLE_TV = Network(TmdbNetworkId.APPLE_TV)
        }
    }
    data class OnStreaming(val mediaType: TmdbMediaType, val watchRegion: String, val watchProviders: List<Int>): DiscoverCategory() {
        companion object {
            fun Netflix(mediaType: TmdbMediaType, watchRegion: String) = OnStreaming(
                mediaType,
                watchRegion,
                listOf(TmdbWatchProviderId.NETFLIX)
            )

            fun AmazonPrimeVideo(mediaType: TmdbMediaType, watchRegion: String) = OnStreaming(
                mediaType,
                watchRegion,
                listOf(TmdbWatchProviderId.AMAZON_PRIME_VIDEO)
            )

            fun AppleTv(mediaType: TmdbMediaType, watchRegion: String) = OnStreaming(
                mediaType,
                watchRegion,
                listOf(TmdbWatchProviderId.APPLE_TV_PLUS)
            )

            fun DisneyPlus(mediaType: TmdbMediaType, watchRegion: String) = OnStreaming(
                mediaType,
                watchRegion,
                listOf(TmdbWatchProviderId.DISNEY_PLUS)
            )
        }
    }

}