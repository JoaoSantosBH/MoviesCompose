package com.brq.hellocompose.presentation.login

import app.cash.turbine.test
import com.brq.hellocompose.CORRECT_PASS
import com.brq.hellocompose.INVALID_PASS
import com.brq.hellocompose.MainDispatcherRule
import com.brq.hellocompose.VALID_NAME
import com.brq.hellocompose.VALID_PASS
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = LoginViewModel()
    }

    @Test
    fun `testing UI states from Login TRIGGERED BY EVENT`() = runTest {
        viewModel.uiSTate.test {
            val initialState = awaitItem()
            assertEquals(initialState, LoginUiStates.Empty)
            viewModel.onEvent(LoginEvent.ValidateNameField(VALID_NAME))
            val resultValidName = awaitItem()
            assertEquals("name", resultValidName.name)
            viewModel.onEvent(LoginEvent.ValidatePassField(VALID_PASS))
            val resultCorrectPass = awaitItem()
            assertFalse(resultCorrectPass.isPassError)
            viewModel.onEvent(LoginEvent.ValidatePassField(INVALID_PASS))
            val resultWrongPass = awaitItem()
            assertTrue(resultWrongPass.isPassError)
            viewModel.onEvent(LoginEvent.ValidatePassField(CORRECT_PASS))
            val correctPassAgain = awaitItem()
            assertFalse(correctPassAgain.isPassError)
            viewModel.onEvent(LoginEvent.ValidateLogin)
            val resultValidLoginState = awaitItem()
            assertTrue(resultValidLoginState.isSuccessLogin)
        }
    }

}