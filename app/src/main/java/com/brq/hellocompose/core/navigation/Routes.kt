package com.brq.hellocompose.core.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object DetailsScreen : Screen("details")
    object LoginScreen : Screen("login")
}