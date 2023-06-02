package br.com.compose.compose_movies_udemy.ui.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import br.com.compose.compose_movies_udemy.components.LoadingLayout
import br.com.compose.compose_movies_udemy.domain.MovieModel
import br.com.compose.compose_movies_udemy.presentation.HomeEvent
import br.com.compose.compose_movies_udemy.presentation.HomeUiStates
import br.com.compose.compose_movies_udemy.ui.home.HomeLayout
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    state: HomeUiStates,
    onEvent: (HomeEvent) -> Unit
){
     Scaffold(
         content = {paddingValues ->
             if (state.isLoading) LoadingLayout(paddingValues)
             else  DetailsLayout(paddingValues, state.popularMovies)
         }
     )
    LaunchedEffect(key1 = Unit) {
        delay(1000)
        onEvent.invoke(HomeEvent.GetMovieList)
    }

}

@Composable
fun DetailsLayout(paddingValues: PaddingValues, popularMovies: List<MovieModel>) {
    Text(text = "NOT YET")
}
