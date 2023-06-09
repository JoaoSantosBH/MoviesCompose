package com.brq.hellocompose.presentation.home

import com.brq.hellocompose.MainDispatcherRule
import com.brq.hellocompose.core.data.local.dao.MovieDao
import com.brq.hellocompose.core.services.Services
import com.brq.hellocompose.data.remote.PopularMoviesResponse
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    val mdb = mockk<MovieDao>()
    val mserv = mockk<Services>()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = HomeViewModel(mserv, mdb)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `testing UI states from HOME`() = runTest {
        assertFalse(viewModel.uiSTate.value.isLoading)
        launch { viewModel.onEvent(HomeEvent.GetMovieList) }
        advanceUntilIdle()
        assertTrue(viewModel.uiSTate.value.isLoading)
        launch { viewModel.handleMovies(PopularMoviesResponse) }
        advanceUntilIdle()
        val expected = 2
        assertEquals(expected, viewModel.uiSTate.value.popularMovies.size)
    }

}


