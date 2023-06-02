package br.com.compose.compose_movies_udemy.presentation.home

import br.com.compose.compose_movies_udemy.domain.MovieModel

data class HomeUiStates(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val popularMovies: List<MovieModel> = listOf()
) {
        companion object {
        val Empty = HomeUiStates()
    }

}
