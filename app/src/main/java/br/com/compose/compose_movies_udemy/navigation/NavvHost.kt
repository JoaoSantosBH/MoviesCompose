package br.com.compose.compose_movies_udemy.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.compose.compose_movies_udemy.presentation.detail.DetailUiStates
import br.com.compose.compose_movies_udemy.presentation.detail.MovieDetailViewModel
import br.com.compose.compose_movies_udemy.presentation.home.HomeUiStates
import br.com.compose.compose_movies_udemy.presentation.home.HomeViewModel
import br.com.compose.compose_movies_udemy.ui.details.DetailsScreen
import br.com.compose.compose_movies_udemy.ui.home.HomeScreen
import br.com.compose.compose_movies_udemy.util.NetworkUtils.Companion.FILM_ID
import br.com.compose.compose_movies_udemy.util.NetworkUtils.Companion.FILM_ID_ARG
import br.com.compose.compose_movies_udemy.util.rememberFlowWithLifecycle
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