package com.brq.hellocompose.features.details.presentation

sealed class DetailEvent {
    object GetMovieDetails: DetailEvent()
    object SetLoadingImage: DetailEvent()
    object FinishLoadingImage: DetailEvent()
    data class FavoriteMovie(val id: Int) : DetailEvent()
    data class UnFavoriteMovie(val id: Int) : DetailEvent()
    data class Error(val message: String) : DetailEvent()


}
