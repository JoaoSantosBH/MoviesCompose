package br.com.compose.compose_movies_udemy.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import br.com.compose.compose_movies_udemy.components.LoadingLayout
import br.com.compose.compose_movies_udemy.presentation.detail.DetailEvent
import br.com.compose.compose_movies_udemy.presentation.detail.DetailUiStates
import br.com.compose.compose_movies_udemy.presentation.home.HomeEvent
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

    Column(modifier = Modifier.padding(paddingValues)) {

        Text(text = state.movie.overview)
    }
}
