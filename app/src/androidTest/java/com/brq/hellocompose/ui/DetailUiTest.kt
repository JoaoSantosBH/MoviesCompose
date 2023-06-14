package com.brq.hellocompose.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.brq.hellocompose.features.details.presentation.DetailUiStates
import com.brq.hellocompose.features.details.ui.DetailsLayout
import com.brq.hellocompose.nodeBackButton
import com.brq.hellocompose.onEventDetail
import com.brq.hellocompose.ui.theme.MoviesComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MoviesComposeTheme {
                DetailsLayout(
                    paddingValues = PaddingValues(),
                    state = DetailUiStates(),
                    onEvent = onEventDetail,
                    navController = navController
                )
            }
        }
    }

    @Test
    fun detailLayoutTest() {
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onNode(hasTestTag(nodeBackButton)).assertIsDisplayed()
        composeTestRule.onNode(hasTestTag(nodeBackButton)).performClick()
    }
}