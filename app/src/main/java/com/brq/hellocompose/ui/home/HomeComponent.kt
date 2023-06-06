package com.brq.hellocompose.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.brq.hellocompose.R
import com.brq.hellocompose.components.HomeToolBarCompose
import com.brq.hellocompose.components.LoadingLayout
import com.brq.hellocompose.domain.MovieModel
import com.brq.hellocompose.domain.PopularMoviesModel
import com.brq.hellocompose.navigation.Screen
import com.brq.hellocompose.presentation.home.HomeEvent
import com.brq.hellocompose.presentation.home.HomeUiStates
import com.brq.hellocompose.util.NetworkUtils.Companion.PATH_PREFIX_URL
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
            else HomeLayout(paddingValues, state.popularMovies, navController, onEvent)
        }
    )
    LaunchedEffect(key1 = Unit) {
        delay(1000)
        if (state.popularMovies.isEmpty()) onEvent.invoke(HomeEvent.GetMovieList)
    }
}


@Composable
fun HomeLayout(
    paddingValues: PaddingValues,
    cards: List<MovieModel>,
    navController: NavHostController,
    onEvent: (HomeEvent) -> Unit
) {

    Column(modifier = Modifier.padding(paddingValues)) {
        TabLayout(onEvent)
        Spacer(modifier = Modifier.height(8.dp))
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
        navController.navigate(Screen.MoviesDetailsScreen.route + "/${card.id}")
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

@Composable
fun TabLayout(onEvent: (HomeEvent) -> Unit) {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf(stringResource(id = R.string.films_tab_title),
        stringResource(id = R.string.films_tab_favorites))

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    icon = {
                        when (index) {
                            0 -> Icon(imageVector = Icons.Sharp.Info, contentDescription = null)
                            1 -> Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                        }
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> onEvent.invoke(HomeEvent.TabMoviesEvent)
            1 ->  onEvent.invoke(HomeEvent.FavMoviesEvent)
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun HomePreview() {
    val onEvent: (HomeEvent) -> Unit = {}
    val navController = rememberAnimatedNavController()
    val items = PopularMoviesModel.DUMB_RETURN_LIST.results
    HomeLayout(PaddingValues(), items, navController, onEvent)
}