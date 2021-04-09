package app.moviebase.tmdb.remote

expect class Resource(name: String) {
    fun readText(): String
}
