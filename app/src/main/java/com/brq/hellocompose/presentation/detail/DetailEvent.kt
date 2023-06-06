package com.brq.hellocompose.presentation.detail

sealed class DetailEvent {
    object GetMovieDetails: DetailEvent()
    object SetLoadingImage: DetailEvent()
    object FinishLoadingImage: DetailEvent()

    data class FavoriteMovie(val id: Int) : DetailEvent()
    data class UnFavoriteMovie(val id: Int) : DetailEvent()


}
