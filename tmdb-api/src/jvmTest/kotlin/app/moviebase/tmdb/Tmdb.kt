package app.moviebase.tmdb

import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.logging.*
import java.io.FileInputStream
import java.nio.file.Paths
import java.util.*

val properties by lazy {
    Properties().apply {
        val parent = Paths.get(System.getProperty("user.dir")).parent
        FileInputStream("$parent/local.properties").use {
            load(it)
        }
    }
}

fun defaultTmdbConfiguration(
    apiKey: String? = null
): TmdbClientConfig.() -> Unit = {
    tmdbApiKey = apiKey ?: properties.getProperty("TMDB_API_KEY")

    useCache = true
    useTimeout = true
    maxRetriesOnException = 3

//        tmdbAuthenticationToken = null

//        tmdbAccountCredentials {
//            val storage = TmdbAccountStorage() // use own class here
//            sessionId { storage.sessionId }
//            requestToken { storage.requestToken }
//            accessToken { storage.accessToken }
//        }

    httpClient(OkHttp) {
        logging {
            logger = TestLogger()
            level = LogLevel.ALL
        }
    }
}

fun buildTmdb4(): Tmdb4 = Tmdb4(defaultTmdbConfiguration())

fun buildTmdb3(apiKey: String? = null): Tmdb3 =
    Tmdb3(defaultTmdbConfiguration(apiKey))

class TestLogger : Logger {

    override fun log(message: String) {
        println("HttpClient: $message")
    }
}
