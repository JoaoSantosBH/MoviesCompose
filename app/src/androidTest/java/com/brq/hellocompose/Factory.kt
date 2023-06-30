package com.brq.hellocompose

import com.brq.hellocompose.core.navigation.Screen
import com.brq.hellocompose.core.util.NetworkUtils
import com.brq.hellocompose.features.details.data.local.entities.FavoriteMovieEntity
import com.brq.hellocompose.features.details.presentation.DetailEvent
import com.brq.hellocompose.features.home.domain.PopularMoviesModel
import com.brq.hellocompose.features.home.presentation.HomeEvent
import com.brq.hellocompose.features.login.presentation.LoginEvent

val fakeItems = PopularMoviesModel.DUMB_RETURN_LIST.results

val onEventHome: (HomeEvent) -> Unit = {}
val onEventDetail: (DetailEvent) -> Unit = {}
val onLoginDetail: (LoginEvent) -> Unit = {}

val expectedRoute = Screen.DetailsScreen.route + NetworkUtils.FILM_ID_ARG

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