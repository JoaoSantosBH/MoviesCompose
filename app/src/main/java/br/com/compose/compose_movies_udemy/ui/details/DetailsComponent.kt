package br.com.compose.compose_movies_udemy.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import br.com.compose.compose_movies_udemy.R
import br.com.compose.compose_movies_udemy.components.LoadingLayout
import br.com.compose.compose_movies_udemy.presentation.detail.DetailEvent
import br.com.compose.compose_movies_udemy.presentation.detail.DetailUiStates
import br.com.compose.compose_movies_udemy.util.NetworkUtils
import coil.compose.AsyncImage
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    state: DetailUiStates,
    onEvent: (DetailEvent) -> Unit
) {
    Scaffold(
        content = { paddingValues ->
            if (state.isLoading) LoadingLayout(paddingValues)
            else DetailsLayout(paddingValues, state)
        }
    )
    LaunchedEffect(key1 = Unit) {
        delay(1000)
        onEvent.invoke(DetailEvent.GetMovieDetails)
    }

}

@Composable
fun DetailsLayout(paddingValues: PaddingValues, state: DetailUiStates) {

    Column(modifier = Modifier) {
        LazyColumn {
            item {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth,
                    model = NetworkUtils.PATH_PREFIX_URL + state.movie.posterPath,
                    placeholder = rememberVectorPainter(image = Icons.Default.Star),
                    error = painterResource(R.drawable.ic_placeholder),
                    contentDescription = state.movie.title
                )

            }

            item {
                Text(text = state.movie.overview)

            }
        }
    }
}
