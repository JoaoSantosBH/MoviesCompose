package com.brq.hellocompose.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brq.hellocompose.core.util.NetworkUtils.Companion.DEFAULT_NUMBER_PAGES
import com.brq.hellocompose.core.util.NetworkUtils.Companion.PORTUGUESE_LANGUAGE
import com.brq.hellocompose.core.util.RequestHandler
import com.brq.hellocompose.core.util.then
import com.brq.hellocompose.core.util.update
import com.brq.hellocompose.features.details.data.local.dao.MovieDao
import com.brq.hellocompose.features.details.data.local.entities.FavoriteMovieEntity
import com.brq.hellocompose.features.details.data.local.entities.toDomain
import com.brq.hellocompose.features.home.data.remote.model.MovieResponse.Companion.toDomain
import com.brq.hellocompose.features.home.data.remote.model.PopularMoviesResponse
import com.brq.hellocompose.features.home.services.HomeServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val service: HomeServices,
    private val db: MovieDao
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiStates> =
        MutableStateFlow(HomeUiStates.Empty)
    var uiSTate: StateFlow<HomeUiStates> = _uiState
    private val pendingActions = MutableSharedFlow<HomeEvent>()

    init {
        handleEvents()
    }

    fun getMoviesList() {
        if (_uiState.value.popularMovies.isEmpty().not()) return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            RequestHandler.doRequest {
                service.getPopularMoviesList(PORTUGUESE_LANGUAGE, DEFAULT_NUMBER_PAGES)
            }.then(
                onSuccess = {
                    handleMovies(it)
                    updateFavorites()
                },
                onError = {
                    handleError(it)

                },
                onFinish = {
                    _uiState.update { it.copy(isLoading = false) }
                }
            )
        }
    }

    private fun handleError(msg: String) {
        _uiState.update { it.copy(isLoading = false, mustShowDialog = true, errorMessage = msg) }
    }

    private fun updateFavorites() {
        var result: List<FavoriteMovieEntity>
        CoroutineScope(Dispatchers.Default).launch {
            result = db.getFavoriteMoviesList()
            _uiState.update { it.copy(favoriteIds = result.toDomain()) }
        }
    }

    fun handleMovies(resp: PopularMoviesResponse) {
        _uiState.update {
            it.copy(
                popularMovies = resp.results.toDomain(),
                cachedMovies = resp.results.toDomain()
            )
        }
    }

    private fun getFavoritesList(): List<Int?> {
        if (_uiState.value.favoriteIds.isEmpty()) {
            return emptyList()
        } else {
            var returnList: List<Int?> = emptyList()
            CoroutineScope(Dispatchers.IO).launch {
                returnList = db.getFavoriteMoviesList().toDomain()
            }
            return returnList
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
                    HomeEvent.UpdateFavorites -> updateFavorites()
                    HomeEvent.DismissDialog -> dismissErrorMessage()
                }
            }
        }
    }

    private fun dismissErrorMessage() {
        _uiState.update { it.copy(mustShowDialog = false) }
    }

    private fun filterAllMovies() {
        _uiState.update { it.copy(popularMovies = _uiState.value.cachedMovies) }

    }

    private fun filterFavMovies() {
        _uiState.update {
            it.copy(popularMovies = _uiState.value.popularMovies.filter {
                _uiState.value.favoriteIds.contains(
                    it.id
                )
            })
        }
    }

}



