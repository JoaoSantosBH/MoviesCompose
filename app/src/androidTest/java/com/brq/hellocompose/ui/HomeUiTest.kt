package com.brq.hellocompose.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.brq.hellocompose.fakeItems
import com.brq.hellocompose.features.home.presentation.HomeUiStates
import com.brq.hellocompose.features.home.ui.HomeLayout
import com.brq.hellocompose.nodeFav
import com.brq.hellocompose.nodeFilm
import com.brq.hellocompose.onEventHome
import com.brq.hellocompose.ui.theme.MoviesComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MoviesComposeTheme {
                HomeLayout(
                    paddingValues = PaddingValues(),
                    cards = fakeItems,
                    navController = navController,
                    onEvent = onEventHome,
                    state = HomeUiStates()
                )
            }
        }
    }

    @Test
    fun homeLayoutTest() {
        composeTestRule.onNodeWithText(nodeFilm).assertIsDisplayed()
        composeTestRule.onNodeWithText(nodeFilm).performClick()
        composeTestRule.onNodeWithText(nodeFav).assertIsDisplayed()
        composeTestRule.onNodeWithText(nodeFav).performClick()
    }

}