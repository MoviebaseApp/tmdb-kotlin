package app.moviebase.tmdb.discover

import app.moviebase.tmdb.core.currentLocalDate
import app.moviebase.tmdb.core.minusWeeks
import app.moviebase.tmdb.core.plusDays
import app.moviebase.tmdb.core.plusWeeks
import app.moviebase.tmdb.model.TmdbDiscover
import app.moviebase.tmdb.model.TmdbDiscoverFilter
import app.moviebase.tmdb.model.TmdbDiscoverMovieSortBy
import app.moviebase.tmdb.model.TmdbDiscoverShowSortBy
import app.moviebase.tmdb.model.TmdbDiscoverTimeRange
import app.moviebase.tmdb.model.TmdbMediaType
import app.moviebase.tmdb.model.TmdbReleaseType

object DiscoverFactory {

    fun createByCategory(category: DiscoverCategory): TmdbDiscover {
        return when (category) {
            DiscoverCategory.NowPlaying -> createNowPlaying()
            DiscoverCategory.Upcoming -> createUpcoming()
            is DiscoverCategory.Popular -> createPopular(category.mediaType)
            is DiscoverCategory.TopRated -> createTopRated(category.mediaType)
            DiscoverCategory.AiringToday -> createAiringToday()
            DiscoverCategory.OnTv -> createOnTv()
            is DiscoverCategory.OnDvd -> createOnDvd()
            is DiscoverCategory.Network -> createNetwork(category.network)
            is DiscoverCategory.OnStreaming -> createOnStreaming(
                category.mediaType,
                category.watchProviders,
                category.watchRegion,
            )
        }
    }

    fun createNowPlaying(): TmdbDiscover.Movie {
        val localDate = currentLocalDate()
        val firstDate = localDate.minusWeeks(6)
        val lastDate = localDate.plusDays(1)

        val discoverTimeRange = TmdbDiscoverTimeRange.Custom(
            firstDate = firstDate.toString(),
            lastDate = lastDate.toString(),
        )

        return TmdbDiscover.Movie(
            releaseDate = discoverTimeRange,
            withReleaseTypes = TmdbDiscoverFilter(items = setOf(TmdbReleaseType.THEATRICAL)),
        )
    }

    fun createAiringToday(): TmdbDiscover.Show {
        val localDate = currentLocalDate().toString()

        return TmdbDiscover.Show(
            airDateGte = localDate,
            airDateLte = localDate,
        )
    }

    fun createOnTv(): TmdbDiscover.Show {
        val airDateGte = currentLocalDate()
        val airDateLte = airDateGte.plusWeeks(2)

        return TmdbDiscover.Show(
            airDateGte = airDateGte.toString(),
            airDateLte = airDateLte.toString(),
        )
    }

    /**
     * e. g. discover/movie?page=1&sort_by=release_date.desc&with_release_type=5
     */
    fun createOnDvd(): TmdbDiscover =
        TmdbDiscover.Movie(
            sortBy = TmdbDiscoverMovieSortBy.RELEASE_DATE,
            withReleaseTypes = TmdbDiscoverFilter(items = setOf(TmdbReleaseType.PHYSICAL)),
        )

    /**
     * e. g. discover/movie?page=1&release_date.lte=2021-05-08&language=de&sort_by=popularity.desc&region=DE&release_date.gte=2021-04-19
     */
    fun createUpcoming(): TmdbDiscover {
        val localDate = currentLocalDate()
        val firstDate = localDate.plusDays(2)
        val lastDate = localDate.plusWeeks(3)

        val discoverTimeRange = TmdbDiscoverTimeRange.Custom(
            firstDate = firstDate.toString(),
            lastDate = lastDate.toString(),
        )

        return TmdbDiscover.Movie(releaseDate = discoverTimeRange)
    }

    fun createPopular(mediaType: TmdbMediaType): TmdbDiscover {
        return when (mediaType) {
            TmdbMediaType.MOVIE -> TmdbDiscover.Movie(sortBy = TmdbDiscoverMovieSortBy.POPULARITY)
            TmdbMediaType.SHOW -> TmdbDiscover.Show(sortBy = TmdbDiscoverShowSortBy.POPULARITY)
            else -> throw IllegalArgumentException("$mediaType type is not supported for discover")
        }
    }

    fun createTopRated(mediaType: TmdbMediaType): TmdbDiscover {
        return when (mediaType) {
            TmdbMediaType.MOVIE -> TmdbDiscover.Movie(sortBy = TmdbDiscoverMovieSortBy.VOTE_AVERAGE, voteCountGte = 200)
            TmdbMediaType.SHOW -> TmdbDiscover.Show(sortBy = TmdbDiscoverShowSortBy.VOTE_AVERAGE, voteCountGte = 200)
            else -> throw IllegalArgumentException("$mediaType type is not supported for discover")
        }
    }

    /**
     * e. g. discover/tv?page=1&with_networks=213&language=de&sort_by=popularity.desc&region=DE
     */
    fun createNetwork(network: Int): TmdbDiscover.Show {
        return TmdbDiscover.Show(
            withNetworks = TmdbDiscoverFilter(items = setOf(network)),
            sortBy = TmdbDiscoverShowSortBy.POPULARITY,
        )
    }

    fun createOnStreaming(mediaType: TmdbMediaType, watchProviders: TmdbDiscoverFilter<Int>, watchRegion: String): TmdbDiscover {
        return when (mediaType) {
            TmdbMediaType.MOVIE -> TmdbDiscover.Movie(
                sortBy = TmdbDiscoverMovieSortBy.POPULARITY,
                withWatchProviders = watchProviders,
                watchRegion = watchRegion,
            )

            TmdbMediaType.SHOW -> TmdbDiscover.Show(
                sortBy = TmdbDiscoverShowSortBy.POPULARITY,
                withWatchProviders = watchProviders,
                watchRegion = watchRegion,
            )

            else -> throw IllegalArgumentException("$mediaType type is not supported for discover")
        }
    }

    fun createForOneYear(mediaType: TmdbMediaType): TmdbDiscover {
        val discoverTimeRange = TmdbDiscoverTimeRange.OneYear(
            year = currentLocalDate().year,
        )

        return when (mediaType) {
            TmdbMediaType.MOVIE -> TmdbDiscover.Movie(releaseDate = discoverTimeRange)
            TmdbMediaType.SHOW -> TmdbDiscover.Show(firstAirDate = discoverTimeRange)
            else -> throw IllegalArgumentException("$mediaType type is not supported for discover")
        }
    }
}
