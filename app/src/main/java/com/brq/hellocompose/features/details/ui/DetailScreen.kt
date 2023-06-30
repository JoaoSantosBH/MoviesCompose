package com.brq.hellocompose.features.details.ui

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.brq.hellocompose.R
import com.brq.hellocompose.core.components.CustomDialogWithDraweableCompose
import com.brq.hellocompose.core.components.LoadingLayout
import com.brq.hellocompose.core.util.NetworkUtils
import com.brq.hellocompose.features.details.presentation.DetailEvent
import com.brq.hellocompose.features.details.presentation.DetailUiStates
import com.brq.hellocompose.ui.theme.OrangeBrqColor
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    state: DetailUiStates,
    onEvent: (DetailEvent) -> Unit,
    navController: NavHostController
) {

    val image = R.drawable.tmdbicon
    val buttonText = R.string.close_label
    val title = R.string.compose_dialog_master_choice_title_dialog_text
    val info = state.errorMessage

    val showDialog = remember { mutableStateOf(false) }
    val positiveDialogClick: () -> Unit = { navController.popBackStack() }

    if (state.mustShowErrorDialog)
        CustomDialogWithDraweableCompose(
            title, info, image, buttonText, positiveDialogClick,
            setShowDialog = { stateDialog ->
                showDialog.value = stateDialog
            }, positiveDialogClick
        )

    Scaffold(
        content = { paddingValues ->
            if (state.isLoading) LoadingLayout(paddingValues)
            else DetailsLayout(paddingValues, state, onEvent, navController)
        }
    )
    LaunchedEffect(key1 = Unit) {
        delay(1000)
        onEvent.invoke(DetailEvent.GetMovieDetails)
    }

}

@Composable
fun DetailsLayout(
    paddingValues: PaddingValues,
    state: DetailUiStates,
    onEvent: (DetailEvent) -> Unit,
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        LazyColumn {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        onLoading = { onEvent.invoke(DetailEvent.SetLoadingImage) },
                        onSuccess = { onEvent.invoke(DetailEvent.FinishLoadingImage) },
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillWidth,
                        model = NetworkUtils.PATH_PREFIX_URL + state.movie.posterPath,
                        placeholder = rememberVectorPainter(image = Icons.Default.Star),
                        error = painterResource(R.drawable.baseline_local_movies_24),
                        contentDescription = state.movie.title
                    )
                    Box(modifier = Modifier.padding(start = 8.dp, top = 16.dp, end = 16.dp)) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_return),
                                modifier = Modifier
                                    .testTag("backButton")
                                    .size(42.dp)
                                    .clickable { navController.popBackStack() },
                                contentDescription = null
                            )
                            Icon(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clickable {
                                        if (state.isFavorite)
                                            onEvent.invoke(DetailEvent.UnFavoriteMovie(state.movie.id))
                                        else
                                            onEvent.invoke(DetailEvent.FavoriteMovie(state.movie.id))
                                    },
                                painter = painterResource(id = R.drawable.icon_heart_toolbar),
                                tint = if (state.isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onError,
                                contentDescription = null
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = state.movie.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(id = R.string.sinopse_text),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelLarge.copy(color = OrangeBrqColor)
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = state.movie.overview,
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
                    )
                }
            }

            item { CardDetails(state) }
        }
    }

}

@Composable
fun CardDetails(state: DetailUiStates) {
    val firstCardIcon = R.drawable.ic_first_card
    val secondCardIcon = R.drawable.ic_second_card
    val thirdCardIcon = R.drawable.ic_third_card
    val fourthCardIcon = R.drawable.ic_fourth_card
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardStatisticLayout(
                state.movie.popularity.toString(),
                firstCardIcon,
                R.string.first_card_title
            )
            Spacer(modifier = Modifier.height(16.dp))
            CardStatisticLayout(
                state.movie.release_date,
                thirdCardIcon,
                R.string.third_card_title
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardStatisticLayout(
                state.movie.voteAverage.toString(),
                secondCardIcon,
                R.string.second_card_title
            )
            Spacer(modifier = Modifier.height(16.dp))
            CardStatisticLayout(
                state.movie.overview,
                fourthCardIcon,
                R.string.fourth_card_title
            )
        }
    }
}



@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = false, backgroundColor = 0xFFF44336
)
@Composable
fun CardPreview() {
    val state = DetailUiStates()
    CardDetails(state)
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(
    device = "id:Galaxy Nexus",
    showSystemUi = false, showBackground = true, backgroundColor = 0xFF536DFE,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE, apiLevel = 31
)
@Composable
fun ScreenPreview() {
    val onEvent: (DetailEvent) -> Unit = {}
    val navController = rememberAnimatedNavController()
    val state = DetailUiStates()
    DetailsScreen(state, onEvent, navController)
}