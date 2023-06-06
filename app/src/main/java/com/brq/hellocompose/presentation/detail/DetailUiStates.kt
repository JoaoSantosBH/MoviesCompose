package com.brq.hellocompose.presentation.detail

import com.brq.hellocompose.domain.MovieDetailModel

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
