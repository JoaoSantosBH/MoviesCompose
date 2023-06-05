package br.com.compose.compose_movies_udemy.presentation.detail

sealed class DetailEvent {
    object GetMovieDetails: DetailEvent()
    object SetLoadingImage: DetailEvent()
    object FinishLoadingImage: DetailEvent()

}
