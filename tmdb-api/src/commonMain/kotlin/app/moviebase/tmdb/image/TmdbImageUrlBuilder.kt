package app.moviebase.tmdb.image

import app.moviebase.tmdb.TmdbWebConfig
import app.moviebase.tmdb.model.TmdbVideo

object TmdbImageUrlBuilder {

    /**
     * Building the URL has three parts:
     * The base URL will look like: http://image.tmdb.org/t/p/.
     * Then you will need a ‘size’, which will be one of the following:
     * "w92", "w154", "w185", "w342", "w500", "w780", or "original". For most phones we recommend using “w185”.
     * And finally the poster path returned by the query, in this case “/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg”
     *
     *
     * Example poster: http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
     * Example logo: https://image.tmdb.org/t/p/h60/ifhbNuuVnlwYy5oXA5VIb2YR8AZ.png
     *
     * Note: Also build for person profile path. Sizes are "w45", "w185", "h632", "original"
     */
    fun build(sizeKey: String, imagePath: String): String {
        var url = TmdbWebConfig.BASE_URL_TMDB_IMAGE + sizeKey
        if (!imagePath.startsWith("/"))
            url += "/"

        return url + imagePath
    }

    fun build(image: TmdbImage, width: Int, height: Int): String {
        val sizeKey = TmdbImageSize.getSizeKey(image.type, width, height)
        return build(sizeKey, image.path)
    }

    /**
     * default 120x90
     * mqdefault 320x180
     * hqdefault 480x360
     * Example http://img.youtube.com/vi/<insert-youtube-video-id-here>/default.jpg</insert-youtube-video-id-here>
     */
    fun buildYoutube(youtubeKey: String, imageFileSize: String): String {
        return TmdbWebConfig.BASE_URL_YOUTUBE_IMAGE + "/" + youtubeKey + "/" + imageFileSize + ".jpg"
    }

    fun buildYoutube(video: TmdbVideo, width: Int): String {
        val imageFileSize = YoutubeImageSize.getYoutubeSizeKey(width)
        return buildYoutube(video.key, imageFileSize)
    }
}
