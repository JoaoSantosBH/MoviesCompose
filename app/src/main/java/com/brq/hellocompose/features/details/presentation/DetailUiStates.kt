package com.brq.hellocompose.features.details.presentation

import com.brq.hellocompose.features.details.domain.MovieDetailModel

data class DetailUiStates(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val movieId: String = "",
    val movie: MovieDetailModel = MovieDetailModel.EMPTY,
    val isFavorite: Boolean = false,
    val errorMessage:String = "",
    val mustShowErrorDialog:Boolean = false
) {

    companion object {
        val Empty = DetailUiStates()
    }
}
