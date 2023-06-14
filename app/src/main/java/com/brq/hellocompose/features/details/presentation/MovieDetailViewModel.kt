package com.brq.hellocompose.features.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brq.hellocompose.core.util.NetworkUtils.Companion.PORTUGUESE_LANGUAGE
import com.brq.hellocompose.core.util.RequestHandler
import com.brq.hellocompose.core.util.then
import com.brq.hellocompose.core.util.update
import com.brq.hellocompose.features.details.data.local.dao.MovieDao
import com.brq.hellocompose.features.details.data.local.entities.toLocal
import com.brq.hellocompose.features.details.data.remote.model.MovieDetailResponse
import com.brq.hellocompose.features.details.data.remote.model.MovieDetailResponse.Companion.toDomain
import com.brq.hellocompose.features.details.services.DetailsServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val service: DetailsServices,
    private val db: MovieDao
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailUiStates> =
        MutableStateFlow(DetailUiStates.Empty)
    var uiSTate: StateFlow<DetailUiStates> = _uiState
    private val pendingActions = MutableSharedFlow<DetailEvent>()

    init { handleEvents() }

    private fun getMoviesDetail() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            RequestHandler.doRequest {
                service.getMovieDetails(_uiState.value.movieId, PORTUGUESE_LANGUAGE) }.then(
                onSuccess = {
                    setUiValues(it)
                },
                onError = {
                    onEvent(DetailEvent.Error(it))
                },
                onFinish = {
                    _uiState.update { it.copy(isLoading = false) }
                }
            )
        }
    }

    private fun setUiValues(mv: MovieDetailResponse) {
        CoroutineScope(Dispatchers.Default).launch {
            _uiState.update { it.copy(movie = mv.toDomain()) }
            val isFavorite = db.checkIfisAFavoriteMovie(_uiState.value.movie.id)
            if (isFavorite) _uiState.update { it.copy(isFavorite = isFavorite) }
        }
    }

    fun setMovieId(movieId: String) {
        _uiState.update { it.copy(movieId = movieId) }
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
                    DetailEvent.SetLoadingImage -> setLoading()
                    DetailEvent.FinishLoadingImage -> finishLoading()
                    is DetailEvent.FavoriteMovie -> favoriteMovie(event.id)
                    is DetailEvent.UnFavoriteMovie -> unFavoriteMovie(event.id)
                    is DetailEvent.Error -> showErrorMessage(event.message)
                }
            }
        }
    }

    private fun showErrorMessage(message: String) {
        _uiState.update { it.copy(isLoading = false, errorMessage = message, mustShowErrorDialog = true) }

    }

    private fun favoriteMovie(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch{
            db.insertFavoriteMovie(movieId.toLocal())
            _uiState.update { it.copy(
                isFavorite = db.checkIfisAFavoriteMovie(movieId)) }
        }
    }
    private fun unFavoriteMovie(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val toRemove = db.getFavoriteMovieById(id)
            db.removeFavoriteMovie(toRemove)
            _uiState.update { it.copy(isFavorite = db.checkIfisAFavoriteMovie(id)) }
        }
    }

    private fun finishLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    private fun setLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }


}