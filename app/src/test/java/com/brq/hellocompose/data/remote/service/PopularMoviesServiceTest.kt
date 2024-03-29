package com.brq.hellocompose.data.remote.service

import com.brq.hellocompose.core.util.NetworkUtils
import com.brq.hellocompose.features.home.data.remote.model.PopularMoviesResponse.Companion.FakeResponse
import com.brq.hellocompose.features.home.services.HomeServices
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PopularMoviesServiceTest {

    val service = mockk<HomeServices>()

    @Before
    fun setup(){

    }
    @Test
    fun `WHEN service is called MUST return Success Response`(){
        runBlocking {
            val expectedResponse = FakeResponse
            stubSuccessResponse()
            val result = service.getPopularMoviesList(NetworkUtils.PORTUGUESE_LANGUAGE,
                NetworkUtils.DEFAULT_NUMBER_PAGES)
            Assert.assertEquals(expectedResponse,result.body())
        }
    }

    @Test(expected = RuntimeException::class)
    fun `WHEN service has Timeout MUST throw EXCEPTION`() {
        runBlocking {
            stubTimeOutFromServer()
            service.getPopularMoviesList( NetworkUtils.PORTUGUESE_LANGUAGE,
                NetworkUtils.DEFAULT_NUMBER_PAGES)
        }
    }

    fun stubTimeOutFromServer() {
        coEvery {
            service.getPopularMoviesList(
                NetworkUtils.PORTUGUESE_LANGUAGE,
                NetworkUtils.DEFAULT_NUMBER_PAGES
            )
        } coAnswers {
            throw RuntimeException()
        }
    }

    private fun stubSuccessResponse() {
        coEvery {
            service.getPopularMoviesList(NetworkUtils.PORTUGUESE_LANGUAGE,
                NetworkUtils.DEFAULT_NUMBER_PAGES)
        } coAnswers {
            Response.success(FakeResponse)
        }
    }
}