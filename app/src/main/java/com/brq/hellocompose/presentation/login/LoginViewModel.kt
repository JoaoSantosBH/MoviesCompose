package com.brq.hellocompose.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<LoginUiStates> =
        MutableStateFlow(LoginUiStates.Empty)
    var uiSTate: StateFlow<LoginUiStates> = _uiState
    private val pendingActions = MutableSharedFlow<LoginEvent>()

    init { handleEvents() }

    fun onEvent(event: LoginEvent) {
        viewModelScope.launch {
            pendingActions.emit(event)
        }
    }

    private fun handleEvents() {
        viewModelScope.launch {
            pendingActions.collect { event ->
                when (event) {
                    is LoginEvent.ValidateLogin -> validatingLogin()
                    is LoginEvent.ValidateNameField -> validateNameField(event)
                    is LoginEvent.ValidatePassField -> validatePassField(event)
                }
            }
        }
    }

    private fun validatePassField(event: LoginEvent.ValidatePassField) {
        _uiState.value = _uiState.value.copy(pass = event.pass)
        if (event.pass.isNotEmpty() && event.pass.length > 4) _uiState.value = _uiState.value.copy(isPassError = false)
        else _uiState.value = _uiState.value.copy(isPassError = true)
    }

    private fun validateNameField(event: LoginEvent.ValidateNameField) {
        _uiState.value = _uiState.value.copy(name = event.name)
        if (event.name.isNotEmpty()) _uiState.value = _uiState.value.copy(isNameError = false)
        else _uiState.value = _uiState.value.copy(isNameError = true)
    }

    private fun validatingLogin() {
        verifyAllFieldsAreFilled()
        if (_uiState.value.pass == uiSTate.value.fakePass &&
            !_uiState.value.isNameError &&
            !_uiState.value.isPassError &&
            _uiState.value.name.isNotEmpty()) {
            _uiState.value = _uiState.value.copy(isSuccessLogin = true)
        }
        else _uiState.value = _uiState.value.copy(isSuccessLogin = false)
    }

    private fun verifyAllFieldsAreFilled() {
        if (_uiState.value.name.isNotEmpty() && _uiState.value.pass.isNotEmpty()) {
            _uiState.value = _uiState.value.copy( allFieldsAreFilled = true)
        } else {
            _uiState.value = _uiState.value.copy( allFieldsAreFilled = false)
        }
    }

}