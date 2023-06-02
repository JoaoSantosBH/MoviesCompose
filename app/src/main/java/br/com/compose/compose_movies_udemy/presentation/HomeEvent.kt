package br.com.compose.compose_movies_udemy.presentation

sealed class HomeEvent {
    object GetMovieList: HomeEvent()
    object NavigateToDetails : HomeEvent()

}