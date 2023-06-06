package com.brq.hellocompose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brq.hellocompose.remote.model.MovieResponse.Companion.toDomain
import com.brq.hellocompose.services.Services
import com.brq.hellocompose.util.NetworkUtils.Companion.DEFAULT_NUMBER_PAGES
import com.brq.hellocompose.util.NetworkUtils.Companion.PORTUGUESE_LANGUAGE
import com.brq.hellocompose.util.RequestHandler
import com.brq.hellocompose.util.then
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val service: Services
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiStates> =
        MutableStateFlow(HomeUiStates.Empty)
    var uiSTate: StateFlow<HomeUiStates> = _uiState
    private val pendingActions = MutableSharedFlow<HomeEvent>()

    init { handleEvents() }

    private fun getMoviesList() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            RequestHandler.doRequest {
                service.getPopularMoviesList(PORTUGUESE_LANGUAGE, DEFAULT_NUMBER_PAGES) }.then(
                onSuccess = {
                    _uiState.value = _uiState.value.copy(popularMovies = it.results.toDomain())
                },
                onError = {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                },
                onFinish = {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            )
        }
    }

    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            pendingActions.emit(event)
        }
    }

    private fun handleEvents() {
        viewModelScope.launch {
            pendingActions.collect { event ->
                when (event) {
                    HomeEvent.GetMovieList -> getMoviesList()
                    HomeEvent.TabMoviesEvent -> filterAllMovies()
                    HomeEvent.FavMoviesEvent -> filterFavMovies()
                }
            }
        }
    }

    private fun filterAllMovies() {
//        _uiState.value = _uiState.value.copy(popularMovies = _uiState.value.cachedMovies)

    }

    private fun filterFavMovies() {
//        _uiState.value = _uiState.value.copy(cachedMovies = _uiState.value.popularMovies)
//        _uiState.value = _uiState.value.copy(popularMovies = _uiState.value.popularMovies.filter { m.contains(it.id) })

    }

    val m = listOf(640146,1107872,758323  )
}

