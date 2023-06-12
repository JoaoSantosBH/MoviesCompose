package com.brq.hellocompose.presentation.login

data class LoginUiStates(
    val isSuccessLogin :Boolean = false,
    val allFieldsAreFilled:Boolean = false,
    val name:String = "",
    val pass:String = "",
    val isNameError:Boolean = false,
    val nameErrorHint : String = "Digite seu nome",
    val isPassError:Boolean = false,
    val passErrorHint : String = "A senha deve conter mais de 4 digitos",
    val fakePass:String = "abc123"
) {
    companion object {
        val Empty = LoginUiStates()
    }
}