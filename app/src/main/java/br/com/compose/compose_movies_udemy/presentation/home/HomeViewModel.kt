package br.com.compose.compose_movies_udemy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.compose.compose_movies_udemy.remote.model.MovieResponse.Companion.toDomain
import br.com.compose.compose_movies_udemy.services.Services
import br.com.compose.compose_movies_udemy.util.NetworkUtils.Companion.DEFAULT_NUMBER_PAGES
import br.com.compose.compose_movies_udemy.util.NetworkUtils.Companion.PORTUGUESE_LANGUAGE
import br.com.compose.compose_movies_udemy.util.RequestHandler
import br.com.compose.compose_movies_udemy.util.then
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

    private fun navigateToDetails() {}

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
                    else -> navigateToDetails()
                }
            }
        }
    }
}

