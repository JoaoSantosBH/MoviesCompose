package com.brq.hellocompose.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.brq.hellocompose.core.navigation.Screen
import com.brq.hellocompose.core.util.NetworkUtils.Companion.FILM_ID
import com.brq.hellocompose.core.util.NetworkUtils.Companion.FILM_ID_ARG
import com.brq.hellocompose.core.util.rememberFlowWithLifecycle
import com.brq.hellocompose.features.details.presentation.DetailUiStates
import com.brq.hellocompose.features.details.presentation.MovieDetailViewModel
import com.brq.hellocompose.features.details.ui.DetailsScreen
import com.brq.hellocompose.features.home.presentation.HomeUiStates
import com.brq.hellocompose.features.home.presentation.HomeViewModel
import com.brq.hellocompose.features.home.ui.HomeScreen
import com.brq.hellocompose.features.login.presentation.LoginUiStates
import com.brq.hellocompose.features.login.presentation.LoginViewModel
import com.brq.hellocompose.features.login.ui.LoginScreen
import org.koin.androidx.compose.getViewModel

@Composable
internal fun AppNavigationTest(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        navigateToLogin(navController)
        navigationHome(navController)
        navigationDetails(navController)
    }
}

fun NavGraphBuilder.navigateToLogin(navController: NavHostController) {
    composable(route = Screen.LoginScreen.route) {
        val viewModel: LoginViewModel = getViewModel()
        val uiState by rememberFlowWithLifecycle(viewModel.uiSTate)
            .collectAsState(initial = LoginUiStates.Empty)
        LoginScreen(
            navController = navController,
            state = uiState,
            onEvent = viewModel::onEvent
        )
    }
}

fun NavGraphBuilder.navigationHome(navController: NavHostController) {
    composable(route = Screen.HomeScreen.route) {
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

fun NavGraphBuilder.navigationDetails(navController: NavHostController) {
    composable(
        route = Screen.DetailsScreen.route + FILM_ID_ARG,
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