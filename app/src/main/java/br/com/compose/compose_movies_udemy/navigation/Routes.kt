package br.com.compose.compose_movies_udemy.navigation

sealed class Screen(val route: String) {
    object MoviesScreen: Screen("home")
    object MoviesDetailsScreen: Screen("details")
}