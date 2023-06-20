package com.brq.hellocompose.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.brq.hellocompose.expectedRoute
import com.brq.hellocompose.fakeItems
import com.brq.hellocompose.features.home.presentation.HomeEvent
import com.brq.hellocompose.features.home.presentation.HomeUiStates
import com.brq.hellocompose.features.home.ui.HomeLayout
import com.brq.hellocompose.nodeRoute
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    val onEvent: (HomeEvent) -> Unit = {}
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavigationTest(navController = navController)

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