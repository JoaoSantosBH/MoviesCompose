package br.com.compose.compose_movies_udemy.presentation.detail

import br.com.compose.compose_movies_udemy.domain.MovieDetailModel

data class DetailUiStates(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val movieId: String = "",
    val movie: MovieDetailModel = MovieDetailModel.EMPTY
) {

    companion object {
        val Empty = DetailUiStates()
    }
}
