package com.brq.hellocompose.presentation.home

import app.cash.turbine.test
import com.brq.hellocompose.MainDispatcherRule
import com.brq.hellocompose.core.data.local.dao.MovieDao
import com.brq.hellocompose.core.data.remote.model.PopularMoviesResponse.Companion.FakeResponse
import com.brq.hellocompose.core.services.Services
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.stub
import retrofit2.Response

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    val mdb = mockk<MovieDao>()

    @Mock
    val mserv :Services = mock()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = HomeViewModel(mserv, mdb)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `testing UI states from HOME TRIGGERED BY EVENT`() = runTest {

        val expectedResult = Response.success(FakeResponse)
        mserv.stub {
            onBlocking { getPopularMoviesList("", 1) } doAnswer { expectedResult }
        }

        viewModel.uiSTate.test {
            val initialState = awaitItem()
            assertEquals(initialState,HomeUiStates.Empty)
            viewModel.onEvent(HomeEvent.GetMovieList)
            val resultIni = awaitItem()
            assertTrue(resultIni.isLoading)
            val next = awaitItem()
            assertFalse(next.isLoading)
        }
    }

}


