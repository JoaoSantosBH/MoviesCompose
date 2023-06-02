package br.com.compose.compose_movies_udemy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.compose.compose_movies_udemy.presentation.HomeUiStates
import br.com.compose.compose_movies_udemy.ui.theme.MoviesComposeTheme
import br.com.compose.compose_movies_udemy.presentation.HomeViewModel
import br.com.compose.compose_movies_udemy.ui.home.HomeScreen
import br.com.compose.compose_movies_udemy.util.rememberFlowWithLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val uiState by rememberFlowWithLifecycle(viewModel.uiSTate)
                        .collectAsState(initial = HomeUiStates.Empty)
                    HomeScreen(
                        state = uiState,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}

