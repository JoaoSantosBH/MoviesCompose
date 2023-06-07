package com.brq.hellocompose.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brq.hellocompose.core.data.local.dao.MovieDao
import com.brq.hellocompose.core.data.local.entities.toLocal
import com.brq.hellocompose.core.data.remote.model.MovieDetailResponse
import com.brq.hellocompose.core.data.remote.model.MovieDetailResponse.Companion.toDomain
import com.brq.hellocompose.core.services.Services
import com.brq.hellocompose.core.util.NetworkUtils.Companion.PORTUGUESE_LANGUAGE
import com.brq.hellocompose.core.util.RequestHandler
import com.brq.hellocompose.core.util.then
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val service: Services,
    private val db: MovieDao
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
                    setUiValues(it)
                },
                onError = {
                    onEvent(DetailEvent.Error(it))
                },
                onFinish = {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            )
        }
    }

    private fun setUiValues(it: MovieDetailResponse) {
        CoroutineScope(Dispatchers.Default).launch {
            _uiState.value = _uiState.value.copy(movie = it.toDomain())
            val isFavorite = db.checkIfisAFavoriteMovie(_uiState.value.movie.id)
            if (isFavorite) _uiState.value = _uiState.value.copy(isFavorite = isFavorite)
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
        _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = message, mustShowErrorDialog = true)

    }

    private fun favoriteMovie(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch{
            db.insertFavoriteMovie(movieId.toLocal())
            _uiState.value = _uiState.value.copy(
                isFavorite = db.checkIfisAFavoriteMovie(movieId))
        }
    }
    private fun unFavoriteMovie(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val toRemove = db.getFavoriteMovieById(id)
            db.removeFavoriteMovie(toRemove)
            _uiState.value = _uiState.value.copy(isFavorite = db.checkIfisAFavoriteMovie(id))
        }
    }

    private fun finishLoading() {
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    private fun setLoading() {
        _uiState.value = _uiState.value.copy(isLoading = true)
    }


}