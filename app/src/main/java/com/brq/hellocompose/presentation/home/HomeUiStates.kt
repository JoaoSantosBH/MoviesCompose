package com.brq.hellocompose.presentation.home

import com.brq.hellocompose.core.domain.MovieModel

data class HomeUiStates(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val popularMovies: List<MovieModel> = emptyList(),
    val cachedMovies: List<MovieModel> = emptyList(),
    val favoriteIds: List<Int?> = emptyList(),
    val mustShowDialog: Boolean = false,
    val errorMessage: String = ""
) {
        companion object {
        val Empty = HomeUiStates()
    }

}
