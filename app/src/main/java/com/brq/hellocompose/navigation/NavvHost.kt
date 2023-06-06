package com.brq.hellocompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.brq.hellocompose.presentation.detail.DetailUiStates
import com.brq.hellocompose.presentation.detail.MovieDetailViewModel
import com.brq.hellocompose.presentation.home.HomeUiStates
import com.brq.hellocompose.presentation.home.HomeViewModel
import com.brq.hellocompose.ui.details.DetailsScreen
import com.brq.hellocompose.ui.home.HomeScreen
import com.brq.hellocompose.util.NetworkUtils.Companion.FILM_ID
import com.brq.hellocompose.util.NetworkUtils.Companion.FILM_ID_ARG
import com.brq.hellocompose.util.rememberFlowWithLifecycle
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.MoviesScreen.route,
        modifier = modifier,
    ) {
        navigateToHome(navController)
        navigateToDetails(navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.navigateToHome(navController: NavHostController) {
    composable(route = Screen.MoviesScreen.route) {
        val viewModel: HomeViewModel = getViewModel()
        val uiState by rememberFlowWithLifecycle(viewModel.uiSTate)
            .collectAsState(initial = HomeUiStates.Empty)
        HomeScreen(
            navController = navController,
            state = uiState,
            onEvent = viewModel::onEvent
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.navigateToDetails(navController: NavHostController) {
    composable(
        route = Screen.MoviesDetailsScreen.route + FILM_ID_ARG,
        arguments = listOf(navArgument(FILM_ID) { type = NavType.StringType })
    ) { backStackEntry ->
        val viewModel: MovieDetailViewModel = getViewModel()
        viewModel.setMovieId(backStackEntry.arguments?.getString(FILM_ID).toString())
        val uiState by rememberFlowWithLifecycle(viewModel.uiSTate)
            .collectAsState(initial = DetailUiStates.Empty)
        DetailsScreen(
            state = uiState,
            onEvent = viewModel::onEvent,
            navController = navController
        )
    }
}