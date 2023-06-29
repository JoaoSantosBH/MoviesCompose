package com.brq.hellocompose.features.home.presentation

sealed class HomeEvent {
    object GetMovieList : HomeEvent()
    object TabMoviesEvent : HomeEvent()
    object FavMoviesEvent : HomeEvent()
    object UpdateFavorites : HomeEvent()
    object DismissDialog : HomeEvent()

}