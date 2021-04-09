package app.moviebase.tmdb.remote

import java.io.File

actual class Resource actual constructor(name: String) {

    private val file = File(name)

    actual fun readText(): String = file.readText()

}
