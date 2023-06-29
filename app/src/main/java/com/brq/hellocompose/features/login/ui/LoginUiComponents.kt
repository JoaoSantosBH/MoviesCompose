package com.brq.hellocompose.features.login.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brq.hellocompose.R
import com.brq.hellocompose.core.util.showToastMessage
import com.brq.hellocompose.features.login.presentation.LoginEvent
import com.brq.hellocompose.features.login.presentation.LoginUiStates
import com.brq.hellocompose.ui.theme.ButtonDisabledColor
import com.brq.hellocompose.ui.theme.OrangeBrqColor
import com.brq.hellocompose.ui.theme.TextFieldContainerColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        containerColor = TextFieldContainerColor,
        cursorColor = Color.White,
        focusedLabelColor = Color.White,
        disabledLabelColor = TextFieldContainerColor,
        textColor = Color.White,
        focusedIndicatorColor = Color.White,
        unfocusedIndicatorColor = Color.Transparent,
        unfocusedLabelColor = Color.White,
        errorLabelColor = Color.White
    )
}

@Composable
fun LogoBrq() {
    Spacer(Modifier.height(40.dp))
    Image(
        modifier = Modifier.size(224.dp),
        painter = painterResource(id = R.drawable.logo_brq),
        contentDescription = null
    )
    Spacer(modifier = Modifier.height(68.dp))
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NameTextField(
    focusRequester: FocusRequester, onEvent: (LoginEvent) -> Unit, state: LoginUiStates
) {

    TextField(modifier = Modifier
        .fillMaxWidth()
        .testTag("Name textField")
        .focusRequester(focusRequester)
        .onFocusChanged { focusState ->
            if (!focusState.isFocused) onEvent.invoke(LoginEvent.ValidateNameField(state.name))
        },
        label = { Text(text = stringResource(id = R.string.hint_name_text)) },
        colors = myTextFieldColors(),
        value = state.name,
        maxLines = 1,
        onValueChange = {
            onEvent.invoke(LoginEvent.ValidateNameField(it))
        },
        leadingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(R.drawable.ic_user),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        isError = state.isNameError,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
        ),
        trailingIcon = {
            IconButton(onClick = { onEvent.invoke(LoginEvent.CleanNameField) }) {
                if (state.name.isNotEmpty()) {
                    Icon(
                        painter = painterResource(R.drawable.ic_close_text_input),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        })
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PassTextField(
    state: LoginUiStates, onEvent: (LoginEvent) -> Unit
) {
    TextField(modifier = Modifier
        .fillMaxWidth()
        .testTag("Pass textField"),
        colors = myTextFieldColors(),
        label = { Text(text = stringResource(id = R.string.hint_pass_text)) },
        value = state.pass,
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = {
            onEvent.invoke(LoginEvent.ValidatePassField(it))
        },
        leadingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(R.drawable.ic_lock),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        isError = state.isPassError,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go, keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(onGo = {
            onEvent.invoke(LoginEvent.ValidateLogin)
        }),
        trailingIcon = {
            IconButton(onClick = { onEvent.invoke(LoginEvent.CleanPassField) }) {
                if(state.pass.isNotEmpty()) {
                    Icon(
                        painter = painterResource(R.drawable.ic_close_text_input),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        })
}

@Composable
fun BottomLoginLayout(
    onEvent: (LoginEvent) -> Unit, context: Context, state: LoginUiStates
) {
    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
        Button(
            onClick = { onEvent.invoke(LoginEvent.ValidateLogin) },
            enabled = state.allFieldsAreFilled,
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = OrangeBrqColor,
                disabledContainerColor = ButtonDisabledColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("Button Login")
                .height(50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.login_label_text),
                color = Color.White,
                 style = MaterialTheme.typography.labelLarge)
        }
    }

    Spacer(modifier = Modifier.height(20.dp))
    ClickableText(
        text = AnnotatedString(stringResource(id = R.string.forgot_pass_text)),
        onClick = { showToastMessage(context, "NOT YET") },
        style = TextStyle(
            fontSize = 14.sp, fontFamily = FontFamily.Default, color = Color.White
        )
    )
}