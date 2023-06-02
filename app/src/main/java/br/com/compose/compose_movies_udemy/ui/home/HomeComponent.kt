package br.com.compose.compose_movies_udemy.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.compose.compose_movies_udemy.R
import br.com.compose.compose_movies_udemy.components.HomeToolBarCompose
import br.com.compose.compose_movies_udemy.components.LoadingLayout
import br.com.compose.compose_movies_udemy.domain.MovieModel
import br.com.compose.compose_movies_udemy.domain.PopularMoviesModel
import br.com.compose.compose_movies_udemy.navigation.Screen
import br.com.compose.compose_movies_udemy.presentation.HomeEvent
import br.com.compose.compose_movies_udemy.presentation.HomeUiStates
import br.com.compose.compose_movies_udemy.util.NetworkUtils.Companion.PATH_PREFIX_URL
import coil.compose.AsyncImage
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    state: HomeUiStates,
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        topBar = {
            HomeToolBarCompose(title = R.string.home_toolbar_title_text)
        },
        content = { paddingValues ->
            if (state.isLoading) LoadingLayout(paddingValues)
            else HomeLayout(paddingValues, state.popularMovies, navController)
        }
    )
    LaunchedEffect(key1 = Unit) {
        delay(1000)
        onEvent.invoke(HomeEvent.GetMovieList)
    }
}


@Composable
fun HomeLayout(
    paddingValues: PaddingValues,
    cards: List<MovieModel>,
    navController: NavHostController
) {

    Column(modifier = Modifier.padding(paddingValues)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(cards) { card ->
                CardMovie(navController, card)
            }
        }
    }
}

@Composable
fun CardMovie(navController: NavHostController, card: MovieModel) {
    Card(modifier = Modifier.clickable {
        navController.navigate(Screen.MoviesDetailsScreen.route)
    }) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth,
            model = PATH_PREFIX_URL + card.posterPath,
            placeholder = rememberVectorPainter(image = Icons.Default.Star),
            error = painterResource(R.drawable.ic_placeholder),
            contentDescription = card.title
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun HomePreview() {
    val navController = rememberAnimatedNavController()
    val items = PopularMoviesModel.DUMB_RETURN_LIST.results
    HomeLayout(PaddingValues(), items, navController)
}