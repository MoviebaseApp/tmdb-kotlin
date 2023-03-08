package app.moviebase.tmdb.image

enum class TmdbImageType {
    POSTER, BACKDROP, PROFILE, LOGO;
}

data class TmdbImage(
    val path: String,
    val type: TmdbImageType
) {

    companion object {

        fun build(path: String?, type: TmdbImageType) = if (path.isNullOrBlank()) null else TmdbImage(path, type)

        fun poster(path: String?) = build(path, TmdbImageType.POSTER)
        fun backdrop(path: String?) = build(path, TmdbImageType.BACKDROP)
        fun profile(path: String?) = build(path, TmdbImageType.PROFILE)
    }
}

object TmdbImageSize {

    const val ORIGINAL = "original"

    const val LOGO_W45 = "w45"
    const val LOGO_W92 = "w92"
    const val LOGO_W154 = "w154"
    const val LOGO_W185 = "w185"
    const val LOGO_W300 = "w300"
    const val LOGO_W500 = "w500"

    const val LOGO_H60 = "h60"
    const val LOGO_H100 = "h100"

    const val PROFILE_W45 = "w45"
    const val PROFILE_W185 = "w185"
    const val PROFILE_H632 = "h632"

    const val POSTER_W92 = "w92"
    const val POSTER_W154 = "w154"
    const val POSTER_W185 = "w185"
    const val POSTER_W342 = "w342"
    const val POSTER_W500 = "w500"
    const val POSTER_W780 = "w780"

    const val BACKDROP_W185 = "w185"
    const val BACKDROP_W300 = "w300"
    const val BACKDROP_W500 = "w500"
    const val BACKDROP_W780 = "w780"
    const val BACKDROP_W1280 = "w1280"

    fun getSizeKey(type: TmdbImageType, width: Int, height: Int): String = when (type) {
        TmdbImageType.POSTER -> getPosterSizeKey(width)
        TmdbImageType.BACKDROP -> getBackdropSizeKey(width)
        TmdbImageType.PROFILE -> getProfileSizeKey(width, height)
        TmdbImageType.LOGO -> getLogoSizeKey(width, height)
    }

    fun getLargestSizeKey(type: TmdbImageType, width: Int) = when (type) {
        TmdbImageType.POSTER -> getPosterSizeKey(if (width < 500) 500 else 780)
        TmdbImageType.BACKDROP -> getBackdropSizeKey(if (width < 780) 780 else 1280)
        TmdbImageType.PROFILE -> getProfileSizeKey(200, 632)
        TmdbImageType.LOGO -> getLogoSizeKey(if (width < 300) 300 else 500, 500)
    }

    fun getLogoSizeKey(width: Int, height: Int): String = when {
        width <= 45 -> LOGO_W45
        height <= 60 -> LOGO_H60
        height <= 100 -> LOGO_H100
        width <= 154 -> LOGO_W154
        width <= 185 -> LOGO_W185
        width <= 300 -> LOGO_W300
        width <= 500 -> LOGO_W500
        else -> ORIGINAL
    }

    fun getPosterSizeKey(width: Int): String = when {
        width <= 92 -> POSTER_W92
        width <= 154 -> POSTER_W154
        width <= 185 -> POSTER_W185
        width <= 342 -> POSTER_W342
        width <= 500 -> POSTER_W500
        width <= 780 -> POSTER_W780
        else -> ORIGINAL
    }

    fun getProfileSizeKey(width: Int, height: Int): String = when {
        width <= 45 -> PROFILE_W45
        width <= 185 -> PROFILE_W185
        else -> if (height <= 632) PROFILE_H632 else ORIGINAL
    }

    fun getBackdropSizeKey(width: Int): String = when {
        width <= 185 -> BACKDROP_W185
        width <= 300 -> BACKDROP_W300
        width <= 500 -> BACKDROP_W500
        width <= 780 -> BACKDROP_W780
        width <= 1280 -> BACKDROP_W1280
        else -> ORIGINAL
    }
}

object YoutubeImageSize {

    const val SIZE_DEFAULT = "default" // 120x90
    const val SIZE_MDEFAULT = "mqdefault" // 320x180
    const val SIZE_HQDEFAULT = "hqdefault" // 480x360

    fun getYoutubeSizeKey(width: Int): String = when {
        width <= 120 -> SIZE_DEFAULT
        width <= 320 -> SIZE_MDEFAULT
        else -> SIZE_HQDEFAULT
    }
}
