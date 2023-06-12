package com.brq.hellocompose

import com.brq.hellocompose.core.data.local.entities.FavoriteMovieEntity
import com.brq.hellocompose.core.domain.PopularMoviesModel
import com.brq.hellocompose.core.navigation.Screen
import com.brq.hellocompose.core.util.NetworkUtils
import com.brq.hellocompose.presentation.detail.DetailEvent
import com.brq.hellocompose.presentation.home.HomeEvent
import com.brq.hellocompose.presentation.login.LoginEvent

val fakeItems = PopularMoviesModel.DUMB_RETURN_LIST.results

val onEventHome: (HomeEvent) -> Unit = {}
val onEventDetail: (DetailEvent) -> Unit = {}
val onLoginDetail: (LoginEvent) -> Unit = {}

val expectedRoute = Screen.MoviesDetailsScreen.route + NetworkUtils.FILM_ID_ARG

const val nodeRoute = "cardMovie640146"
const val nodeFilm = "Filmes"
const val nodeFav = "Favoritos"
const val nodeBackButton = "backButton"
const val nodeLoginTitle  = "Login title"
const val nodeLoginNameTextField = "Name textField"
const val nodeLoginPassTextField = "Pass textField"
const val nodeLoginButton = "Button Login"

const val  DUMB_MOVIE_ID = 12345

fun createFakeMovie() = FavoriteMovieEntity(
    movieId = 12345
)