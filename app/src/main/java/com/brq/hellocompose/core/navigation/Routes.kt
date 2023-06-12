package com.brq.hellocompose.core.navigation

sealed class Screen(val route: String) {
    object MoviesScreen : Screen("home")
    object MoviesDetailsScreen : Screen("details")
    object LoginScreen : Screen("login")
}