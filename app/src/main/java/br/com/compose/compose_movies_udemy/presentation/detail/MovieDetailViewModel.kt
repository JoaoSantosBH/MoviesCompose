package br.com.compose.compose_movies_udemy.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.compose.compose_movies_udemy.remote.model.MovieDetailResponse.Companion.toDomain
import br.com.compose.compose_movies_udemy.services.Services
import br.com.compose.compose_movies_udemy.util.NetworkUtils.Companion.PORTUGUESE_LANGUAGE
import br.com.compose.compose_movies_udemy.util.RequestHandler
import br.com.compose.compose_movies_udemy.util.then
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val service: Services
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailUiStates> =
        MutableStateFlow(DetailUiStates.Empty)
    var uiSTate: StateFlow<DetailUiStates> = _uiState
    private val pendingActions = MutableSharedFlow<DetailEvent>()

    init { handleEvents() }

    private fun getMoviesDetail() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            RequestHandler.doRequest {
                service.getMovieDetails(_uiState.value.movieId, PORTUGUESE_LANGUAGE) }.then(
                onSuccess = {
                    _uiState.value = _uiState.value.copy(movie = it.toDomain())
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

    fun setMovieId(movieId: String) {
        _uiState.value = _uiState.value.copy(movieId = movieId)
    }

    fun onEvent(event: DetailEvent) {
        viewModelScope.launch {
            pendingActions.emit(event)
        }
    }

    private fun handleEvents() {
        viewModelScope.launch {
            pendingActions.collect { event ->
                when (event) {
                    DetailEvent.GetMovieDetails -> getMoviesDetail()
                    else -> {}
                }
            }
        }
    }

}