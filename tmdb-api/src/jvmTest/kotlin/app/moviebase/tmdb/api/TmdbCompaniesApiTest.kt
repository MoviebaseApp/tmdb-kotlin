package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.TmdbCompanyId
import app.moviebase.tmdb.remote.mockHttpClient
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class TmdbCompaniesApiTest {

    val client = mockHttpClient(
        version = 3,
        responses = mapOf(
            "company/145174" to "company/company_details_145174.json"
        )
    )

    val classToTest = TmdbCompaniesApi(client)

    @Test
    fun `it can get company details`() = runTest {
        val companyDetail = classToTest.getDetails(TmdbCompanyId.NETFLIX_INTERNATIONAL_PICTURES)

        assertThat(companyDetail.id).isEqualTo(145174)
        assertThat(companyDetail.name).isEqualTo("Netflix International Pictures")
        assertThat(companyDetail.logoPath).isNull()
    }
}
