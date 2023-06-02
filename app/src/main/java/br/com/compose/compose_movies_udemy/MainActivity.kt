package br.com.compose.compose_movies_udemy

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import br.com.compose.compose_movies_udemy.navigation.AppNavigation
import br.com.compose.compose_movies_udemy.presentation.HomeViewModel
import br.com.compose.compose_movies_udemy.ui.theme.MoviesComposeTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel : HomeViewModel by viewModel()
    var counter = 0
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        delaySplash()
        setContent {
            MoviesComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberAnimatedNavController()
                    AppNavigation(navController = navController)
                }
            }
        }

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (counter == 1) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else { false }
                }
            }
        )
    }

    private fun delaySplash() {
        lifecycleScope.launch {
            delay(2000)
            counter = 1
        }
    }
}




