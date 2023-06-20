package com.brq.hellocompose.features.home.ui

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.brq.hellocompose.R
import com.brq.hellocompose.core.components.CustomDialogWithDraweableCompose
import com.brq.hellocompose.core.components.HomeToolBarCompose
import com.brq.hellocompose.core.components.LoadingLayout
import com.brq.hellocompose.core.navigation.Screen
import com.brq.hellocompose.core.util.NetworkUtils.Companion.PATH_PREFIX_URL
import com.brq.hellocompose.features.home.domain.MovieModel
import com.brq.hellocompose.features.home.domain.PopularMoviesModel
import com.brq.hellocompose.features.home.presentation.HomeEvent
import com.brq.hellocompose.features.home.presentation.HomeUiStates
import com.brq.hellocompose.ui.theme.Cyan700
import com.brq.hellocompose.ui.theme.Green100
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    state: HomeUiStates,
    onEvent: (HomeEvent) -> Unit
) {

    val image = R.drawable.tmdbicon
    val buttonText = R.string.close_label
    val title = R.string.compose_dialog_master_choice_title_dialog_text
    val info = state.errorMessage

    val showDialog = remember { mutableStateOf(false) }
    val positiveDialogClick: () -> Unit = {onEvent.invoke(HomeEvent.DismissDialog)}

    if (state.mustShowDialog)
        CustomDialogWithDraweableCompose(
            title, info, image, buttonText, positiveDialogClick,
            setShowDialog = { stateDialog ->
                showDialog.value = stateDialog
            }, positiveDialogClick
        )

    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(drawerContent = {
        AppDrawer(
            route = Screen.MoviesScreen.route,
            navigateToOther = {  },
            navigateToAnother = {  },
            closeDrawer = { coroutineScope.launch { drawerState.close() } },
            modifier = Modifier
        )
    }, drawerState = drawerState) {

        val menuClick : () -> Unit = { coroutineScope.launch { drawerState.open() }}

        Scaffold(
            topBar = {
                HomeToolBarCompose(title = R.string.home_toolbar_title_text, navController, menuClick)
            },
            content = { paddingValues ->
                HomeLayout(paddingValues, state.popularMovies, navController, onEvent, state)
            }
        )
    }

    LaunchedEffect(key1 = Unit) {
        delay(1000)
        onEvent.invoke(HomeEvent.UpdateFavorites)
        if (state.popularMovies.isEmpty()) onEvent.invoke(HomeEvent.GetMovieList)
    }
    BackHandler {}
}

@Composable
fun HomeLayout(
    paddingValues: PaddingValues,
    cards: List<MovieModel>,
    navController: NavHostController,
    onEvent: (HomeEvent) -> Unit,
    state: HomeUiStates
) {

    Column(modifier = Modifier
        .padding(paddingValues)
        .background(color = Green100)
        .fillMaxSize()) {

        TabLayout(onEvent)

        if (state.isLoading) {
            LoadingLayout(paddingValues)
        } else {
            Spacer(modifier = Modifier.height(8.dp))
            if (cards.isEmpty()) {
                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    if (state.isTabFavSelected) Text(text = stringResource(id = R.string.no_movies_yet))
                }
            } else {

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
    }
}

@Composable
fun CardMovie(navController: NavHostController, card: MovieModel) {
    Card(modifier = Modifier
        .testTag("cardMovie${card.id}")
        .clickable {
        navController.navigate(Screen.MoviesDetailsScreen.route + "/${card.id}")
    }) {

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth,
            model = ImageRequest.Builder(LocalContext.current)
                .data(PATH_PREFIX_URL + card.posterPath)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.baseline_local_movies_24),
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
        TabRow(selectedTabIndex = tabIndex, containerColor = Green100) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    icon = {
                        when (index) {
                            0 -> Icon(imageVector = Icons.Sharp.Info, contentDescription = null, tint = Cyan700)
                            1 -> Icon(imageVector = Icons.Default.Favorite, contentDescription = null, tint = Cyan700)
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
@Preview(device = "id:Nexus 6P", apiLevel = 31,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun HomePreview() {
    val onEvent: (HomeEvent) -> Unit = {}
    val navController = rememberAnimatedNavController()
    val items = PopularMoviesModel.DUMB_RETURN_LIST.results
    HomeLayout(PaddingValues(), items, navController, onEvent, HomeUiStates())
}