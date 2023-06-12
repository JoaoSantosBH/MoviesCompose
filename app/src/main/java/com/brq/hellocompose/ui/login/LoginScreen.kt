package com.brq.hellocompose.ui.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.brq.hellocompose.R
import com.brq.hellocompose.core.navigation.Screen
import com.brq.hellocompose.core.util.showToastMessage
import com.brq.hellocompose.presentation.login.LoginEvent
import com.brq.hellocompose.presentation.login.LoginUiStates
import com.brq.hellocompose.ui.theme.Cyan700
import com.brq.hellocompose.ui.theme.Green100

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
            navController.navigate(Screen.MoviesScreen.route)
        else
            if (state.allFieldsAreFilled && state.isSuccessLogin.not()) showToastMessage(
                context = context,
                loginFailMessage
            )
    }
    LaunchedEffect(key1 = state.name) {
        focusRequester.requestFocus()
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
            .background(color = Green100), contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(painter = painterResource(id = R.drawable.tmdbicon), contentDescription = null)
            Text(
                modifier = Modifier.testTag("Login title"),
                text = stringResource(id = R.string.login_label_text),
                style = TextStyle(fontSize = 22.sp, fontFamily = FontFamily.Monospace)
            )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier
                    .testTag("Name textField")
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused)
                        onEvent.invoke(LoginEvent.ValidateNameField(state.name))
                                    },
                label = { Text(text = stringResource(id = R.string.hint_name_text)) },
                value = state.name,
                onValueChange = {
                    onEvent.invoke(LoginEvent.ValidateNameField(it))
                },
                isError = state.isNameError,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )
            if (state.isNameError) {
                Text(
                    text = state.nameErrorHint,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                modifier = Modifier.testTag("Pass textField"),
                label = { Text(text = stringResource(id = R.string.hint_pass_text)) },
                value = state.pass,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {
                    onEvent.invoke(LoginEvent.ValidatePassField(it))
                },
                isError = state.isPassError,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onGo = {
                        onEvent.invoke(LoginEvent.ValidateLogin)
                    }
                )
            )
            if (state.isPassError) {
                Text(
                    text = state.passErrorHint,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {
                        onEvent.invoke(LoginEvent.ValidateLogin)

                    },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Cyan700),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("Button Login")
                        .height(50.dp)
                ) {
                    Text(text = stringResource(id = R.string.login_label_text))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.forgot_pass_text)),
                onClick = { showToastMessage(context, "NOT YET") },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default
                )
            )
        }
    }

}

@Preview
@Composable
fun LoginPreview() {
    val context = LocalContext.current
    val onEvent: (LoginEvent) -> Unit = {}
    LoginLayout(PaddingValues(), onEvent, LoginUiStates(), context, FocusRequester())
}