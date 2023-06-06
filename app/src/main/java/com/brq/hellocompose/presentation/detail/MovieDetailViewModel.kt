package com.brq.hellocompose.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brq.hellocompose.local.dao.MovieDao
import com.brq.hellocompose.local.entities.toLocal
import com.brq.hellocompose.remote.model.MovieDetailResponse.Companion.toDomain
import com.brq.hellocompose.services.Services
import com.brq.hellocompose.util.NetworkUtils.Companion.PORTUGUESE_LANGUAGE
import com.brq.hellocompose.util.RequestHandler
import com.brq.hellocompose.util.then
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
                    DetailEvent.SetLoadingImage -> setLoading()
                    DetailEvent.FinishLoadingImage -> finishLoading()
                    is DetailEvent.FavoriteMovie -> favoriteMovie(event.id)
                    is DetailEvent.UnFavoriteMovie -> unfavoriteMovie(event.id)
                }
            }
        }
    }

    private fun favoriteMovie(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch{
            db.insertFavoriteMovie(movieId.toLocal())
            _uiState.value = _uiState.value.copy(
                isFavorite = db.checkIfisAFavoriteMovie(movieId))
        }
    }
    private fun unfavoriteMovie(id: Int) {
    val toRemove = db.getFavoriteMovieById(id)
        db.removeFavoriteMovie(toRemove)
        _uiState.value = _uiState.value.copy(isFavorite = db.checkIfisAFavoriteMovie(id))
    }

    private fun finishLoading() {
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    private fun setLoading() {
        _uiState.value = _uiState.value.copy(isLoading = true)
    }


}