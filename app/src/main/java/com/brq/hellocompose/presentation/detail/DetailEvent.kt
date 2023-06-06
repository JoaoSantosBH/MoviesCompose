package com.brq.hellocompose.presentation.detail

sealed class DetailEvent {
    object GetMovieDetails: DetailEvent()
    object SetLoadingImage: DetailEvent()
    object FinishLoadingImage: DetailEvent()

}
