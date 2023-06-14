package com.brq.hellocompose.presentation.home

import app.cash.turbine.test
import com.brq.hellocompose.MainDispatcherRule
import com.brq.hellocompose.features.details.data.local.dao.MovieDao
import com.brq.hellocompose.features.home.data.remote.model.PopularMoviesResponse.Companion.FakeResponse
import com.brq.hellocompose.features.home.presentation.HomeEvent
import com.brq.hellocompose.features.home.presentation.HomeUiStates
import com.brq.hellocompose.features.home.presentation.HomeViewModel
import com.brq.hellocompose.features.home.services.HomeServices
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.stub
import retrofit2.Response

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @Mock
    val mdb: MovieDao = mock()

    @Mock
    val mserv :HomeServices = mock()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        openMocks(mdb)
        openMocks(mserv)
        viewModel = HomeViewModel(mserv, mdb)
    }

    @Test
    fun `testing Home uiStates triggered by events`() = runTest {

        val expectedResult = Response.success(FakeResponse)

        mserv.stub {
            onBlocking { getPopularMoviesList("A", 1) } doAnswer { expectedResult }
        }

        viewModel.uiSTate.test {
            val initialState = awaitItem()
            assertEquals(initialState, HomeUiStates.Empty)
            viewModel.onEvent(HomeEvent.GetMovieList)
            val resultIni = awaitItem()
            assertTrue(resultIni.isLoading)
        }
    }

}


