package com.brq.hellocompose.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.brq.hellocompose.core.navigation.AppNavigation
import com.brq.hellocompose.expectedRoute
import com.brq.hellocompose.fakeItems
import com.brq.hellocompose.nodeRoute
import com.brq.hellocompose.presentation.home.HomeEvent
import com.brq.hellocompose.presentation.home.HomeUiStates
import com.brq.hellocompose.ui.home.HomeLayout
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    /**
     *  Para este teste de navegação em específico
     *  substitua o componente [AnimatedNavHost] no arquivo [NavvHost]
     *  pelo [NavHost] padrao do compose, exemplo no arquivo [NavHostForTest]
     *  pois o AnimatedNavHost nao eh compativel com testes ainda
     *
     *  Depois altere na [MainActivity]  de [val navController = rememberAnimatedNavController()]
     *  para [val navController = rememberNavController()]
     * **/

    @get:Rule
    val composeTestRule = createComposeRule()
    val onEvent: (HomeEvent) -> Unit = {}
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavigation(navController = navController)

            HomeLayout(
                paddingValues = PaddingValues(),
                cards = fakeItems,
                navController = navController,
                onEvent = onEvent,
                state = HomeUiStates()
            )
        }
    }

    @Test
    fun appNavHost_Routes() {
        composeTestRule.waitForIdle()
        composeTestRule.onNode(hasTestTag(nodeRoute)).performClick()
        val route = navController.currentBackStackEntry?.destination?.route
        Assert.assertEquals(expectedRoute, route)
    }

}