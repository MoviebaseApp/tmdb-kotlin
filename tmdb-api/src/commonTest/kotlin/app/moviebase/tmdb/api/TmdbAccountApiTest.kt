package app.moviebase.tmdb.api

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

// this is an example how the tests looks like
class TmdbAccountApiTest {

    val client = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                when (request.url.toString()) {
                    "https://www.themoviedb.org/3/account?session_id=sessionId" -> {
                        val responseHeaders = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
                        respond("Here is json", headers = responseHeaders)
                    }
                    else -> error("Unhandled ${request.url}")
                }
            }
        }
    }

    val classToTest = TmdbAccountApi(client)

    @Test
    fun testGetAccountDetails() = runBlocking {
//        val accountDetails = classToTest.getDetails("sessionId")

    }

}
