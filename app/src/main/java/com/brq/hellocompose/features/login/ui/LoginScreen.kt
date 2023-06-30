package com.brq.hellocompose.features.login.ui

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.brq.hellocompose.R
import com.brq.hellocompose.core.navigation.Screen
import com.brq.hellocompose.core.util.showToastMessage
import com.brq.hellocompose.features.login.presentation.LoginEvent
import com.brq.hellocompose.features.login.presentation.LoginUiStates

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    state: LoginUiStates,
    onEvent: (LoginEvent) -> Unit
) {
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val loginFailMessage = stringResource(id = R.string.fail_login_text)

    Scaffold(
        content = { paddingValues ->
            LoginLayout(paddingValues, onEvent, state, context, focusRequester)
        }
    )
    SideEffect {
        if (state.isSuccessLogin)
            navController.navigate(Screen.HomeScreen.route)
        else
            if (state.allFieldsAreFilled && state.isLoginError) showToastMessage(
                context = context,
                loginFailMessage
            )
    }
    LaunchedEffect(key1 = state.name) {
//        focusRequester.requestFocus()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginLayout(
    paddingValues: PaddingValues,
    onEvent: (LoginEvent) -> Unit,
    state: LoginUiStates,
    context: Context,
    focusRequester: FocusRequester
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = MaterialTheme.colorScheme.background), contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn {
            item {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    LogoBrq()

                    NameTextField(focusRequester, onEvent, state)
                    if (state.isNameError) {
                        Text(
                            text = state.nameErrorHint,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(48.dp))

                    PassTextField(state, onEvent)
                    if (state.isPassError) {
                        Text(
                            text = state.passErrorHint,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(48.dp))
                    BottomLoginLayout(onEvent, context, state)
                }
            }
        }
    }

}




@Preview(device = "id:Nexus 5",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.NONE, showBackground = true
)
@Composable
fun LoginPreview() {
    val context = LocalContext.current
    val onEvent: (LoginEvent) -> Unit = {}
    LoginLayout(PaddingValues(), onEvent, LoginUiStates(), context, FocusRequester())
}