package com.brq.hellocompose.ui.details

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.brq.hellocompose.R
import com.brq.hellocompose.components.LoadingLayout
import com.brq.hellocompose.presentation.detail.DetailEvent
import com.brq.hellocompose.presentation.detail.DetailUiStates
import com.brq.hellocompose.ui.theme.Grey900
import com.brq.hellocompose.util.NetworkUtils
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    state: DetailUiStates,
    onEvent: (DetailEvent) -> Unit,
    navController: NavHostController
) {
    Scaffold(
        content = { paddingValues ->
            if (state.isLoading) LoadingLayout(paddingValues)
            else DetailsLayout(paddingValues, state, onEvent,navController)
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

    Column(modifier = Modifier.padding(paddingValues)) {
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
                        error = painterResource(R.drawable.ic_placeholder),
                        contentDescription = state.movie.title
                    )
                    Box(modifier = Modifier.padding(start = 8.dp, top = 16.dp)) {
                        Icon(
                            modifier = Modifier
                                .size(42.dp)
                                .clickable { navController.popBackStack() },
                            imageVector = Icons.Sharp.ArrowBack,
                            tint = Grey900,
                            contentDescription = null
                        )
                    }
                }
            }

            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) ) {
                    Text(text = state.movie.overview)
                }
            }
            item { CardDetails(state) }
        }
    }
}

@Composable
fun CardDetails(state: DetailUiStates) {

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
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    content = {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.height(8.dp))
                            Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                            Text(
                                state.movie.budget.toString(),
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                )
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                border = BorderStroke(1.dp, Color.Gray),
                content = {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Icon(imageVector = Icons.Default.Info, contentDescription = null)
                        Text(
                            state.movie.revenue.toString(),
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            )

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                border = BorderStroke(1.dp, Color.Gray),
                content = {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Icon(imageVector = Icons.Default.Star, contentDescription = null)
                        Text(
                            state.movie.popularity.toString(),
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                border = BorderStroke(1.dp, Color.Gray),
                content = {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                        Text(
                            state.movie.release_date.toString(),
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            )

        }
    }
}


@Preview
@Composable
fun CardPreview(){
    val state = DetailUiStates()
    CardDetails( state)
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun ScreenPreview(){
    val onEvent: (DetailEvent) -> Unit = {}
    val navController = rememberAnimatedNavController()
    val state = DetailUiStates()
    DetailsScreen( state,onEvent,navController)
}