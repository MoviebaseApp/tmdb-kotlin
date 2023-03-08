package app.moviebase.tmdb

import app.moviebase.tmdb.model.Tmdb4RedirectToBodyAuth
import app.moviebase.tmdb.model.Tmdb4RequestTokenBody
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

// TODO: Enable test when move into integration test folder + own source set
@Disabled
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TmdbAccountIntegrationTest {

    val storage = TmdbAccountStorage()
    val tmdbCredentials = createTmdbSessionCredentials()

    @Nested
    inner class `when using authentication from TMDB version 3` {

        val tmdb3 = buildTmdb3(tmdbAccountStorage = storage)

        init {
            tmdbCredentials.sessionId?.let {
                storage.sessionId = it
            }
        }

        /**
         * Build up the app URL for authentication.
         * Example https://www.themoviedb.org/authenticate/57e299f02ff5309efcdab5b2c26c8ca80aadfsdfsd7?redirect_to=auth://app
         */
        @Test
        fun `it requests the session and gets the request token`() = runTest {
            val requestToken = tmdb3.authentication.requestToken()
            assertThat(requestToken.success).isTrue()
        }

        @Test
        fun `it can validate token from already approved user`() = runTest {
            if (tmdbCredentials.userName == null || tmdbCredentials.password == null) return@runTest

            val token = requireNotNull(tmdbCredentials.approvedRequestToken)

            val requestToken = tmdb3.authentication.validateToken(
                tmdbCredentials.userName,
                tmdbCredentials.password,
                token
            )
            assertThat(requestToken.success).isTrue()
        }

        @Disabled
        @Test
        fun `it can build up session from approved request token`() = runTest {
            val approvedRequestToken = tmdbCredentials.approvedRequestToken ?: return@runTest

            val session = tmdb3.authentication.createSession(approvedRequestToken)
            assertThat(session.success).isTrue()
            assertThat(session.sessionId).isNotNull()
            storage.sessionId = session.sessionId
        }

        @Test
        fun `it fetches account details when the session ID is available`() = runTest {
            requireNotNull(storage.sessionId)

            val details = tmdb3.account.getDetails()
            assertThat(details.id).isNotNull()
            assertThat(details.userName).isNotNull()
        }

        @Test
        fun `it fetches favorites from the account when the session ID is available`() = runTest {
            requireNotNull(storage.sessionId)

            val pageResult = tmdb3.account.getFavoriteMovies(18029486)
            assertThat(pageResult.page).isEqualTo(1)
            assertThat(pageResult.results).isNotEmpty()
        }
    }

    @Nested
    inner class `when using authentication from TMDB version 4` {

        val tmdb4 = buildTmdb4(tmdbAccountStorage = storage)

        init {
            tmdbCredentials.accessTokenVersion4?.let {
                storage.accessToken = it
            }
        }

        @Test
        fun `it can request a new token for auth`() = runTest {
            val redirectTo = "auth://app"
            val requestToken = tmdb4.auth.requestToken(Tmdb4RedirectToBodyAuth(redirectTo))

            assertThat(requestToken.success).isTrue()
        }

        @Disabled
        @Test
        fun `request new access token by an approved request token`() = runTest {
            val approvedRequestToken = tmdbCredentials.approvedRequestTokenVersion4 ?: return@runTest
            val accessToken = tmdb4.auth.accessToken(Tmdb4RequestTokenBody(approvedRequestToken))
            if (accessToken.success) {
                storage.accessToken = accessToken.accessToken
            }
        }

        @Test
        fun `when account session is available it can return lists`() = runTest {
            val accountId = requireNotNull(tmdbCredentials.accountId4)
            val listsResult = tmdb4.account.getLists(accountId, 1)

            assertThat(listsResult.page).isEqualTo(1)
            assertThat(listsResult.results).isNotEmpty()
        }
    }
}
