package com.brq.hellocompose.navigation

sealed class Screen(val route: String) {
    object MoviesScreen: Screen("home")
    object MoviesDetailsScreen: Screen("details")
}