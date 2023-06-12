package com.brq.hellocompose.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.brq.hellocompose.nodeLoginButton
import com.brq.hellocompose.nodeLoginNameTextField
import com.brq.hellocompose.nodeLoginPassTextField
import com.brq.hellocompose.nodeLoginTitle
import com.brq.hellocompose.onLoginDetail
import com.brq.hellocompose.presentation.login.LoginUiStates
import com.brq.hellocompose.ui.login.LoginLayout
import com.brq.hellocompose.ui.theme.MoviesComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController


    @Before
    fun setup() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            val context = LocalContext.current
            val focus = FocusRequester()
            MoviesComposeTheme {
                LoginLayout(
                    paddingValues = PaddingValues(),
                    state = LoginUiStates(),
                    onEvent = onLoginDetail,
                    context = context,
                    focusRequester = focus
                )
            }
        }
    }

    @Test
    fun loginLayoutTest() {

        composeTestRule.onNodeWithTag(nodeLoginTitle).assertIsDisplayed()
        composeTestRule.onNodeWithTag(nodeLoginNameTextField).assertIsDisplayed()
        composeTestRule.onNodeWithTag(nodeLoginNameTextField).performClick()

        composeTestRule.onNodeWithTag(nodeLoginPassTextField).assertIsDisplayed()
        composeTestRule.onNodeWithTag(nodeLoginPassTextField).performClick()

        composeTestRule.onNodeWithTag(nodeLoginButton).assertIsDisplayed()
        composeTestRule.onNodeWithTag(nodeLoginButton).performClick()

    }

}