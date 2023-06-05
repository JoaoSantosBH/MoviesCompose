package br.com.compose.compose_movies_udemy.presentation.home

sealed class HomeEvent {
    object GetMovieList: HomeEvent()
    object TabMoviesEvent : HomeEvent()
    object FavMoviesEvent : HomeEvent()

}