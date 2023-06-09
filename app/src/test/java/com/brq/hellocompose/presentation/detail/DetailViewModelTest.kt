package com.brq.hellocompose.presentation.detail

import com.brq.hellocompose.MainDispatcherRule
import com.brq.hellocompose.core.data.local.dao.MovieDao
import com.brq.hellocompose.core.services.Services
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: MovieDetailViewModel
    val mdb = mockk<MovieDao>()
    val mserv = mockk<Services>()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(mserv,mdb)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `GET MOVIE DETAILS for HOME UI STATES TEST`() = runTest {
        assertFalse( viewModel.uiSTate.value.isLoading)
        launch { viewModel.onEvent(DetailEvent.GetMovieDetails) }
        advanceUntilIdle()
        assertTrue(viewModel.uiSTate.value.isLoading)
    }
}