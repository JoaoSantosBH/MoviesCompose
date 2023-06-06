package com.brq.hellocompose.presentation.home

sealed class HomeEvent {
    object GetMovieList: HomeEvent()
    object TabMoviesEvent : HomeEvent()
    object FavMoviesEvent : HomeEvent()

}