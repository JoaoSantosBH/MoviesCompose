package com.brq.hellocompose.presentation.home

import com.brq.hellocompose.domain.MovieModel

data class HomeUiStates(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val popularMovies: List<MovieModel> = emptyList(),
    val cachedMovies: List<MovieModel> = emptyList()
) {
        companion object {
        val Empty = HomeUiStates()
    }

}
