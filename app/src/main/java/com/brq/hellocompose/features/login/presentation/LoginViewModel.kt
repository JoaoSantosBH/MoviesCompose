package com.brq.hellocompose.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brq.hellocompose.core.util.update
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
        _uiState.update { it.copy(pass = event.pass) } 
        if (event.pass.isNotEmpty() && event.pass.length > 4) _uiState.update { it.copy(isPassError = false) }
        else _uiState.update { it.copy(isPassError = true) }
    }

    private fun validateNameField(event: LoginEvent.ValidateNameField) {
        _uiState.update { it.copy(name = event.name) }
        if (event.name.isNotEmpty()) _uiState.update { it.copy(isNameError = false) }
        else _uiState.update { it.copy(isNameError = true) }
    }

    private fun validatingLogin() {
        verifyAllFieldsAreFilled()
        if (_uiState.value.pass == uiSTate.value.fakePass &&
            !_uiState.value.isNameError &&
            !_uiState.value.isPassError &&
            _uiState.value.name.isNotEmpty()) {
            _uiState.update { it.copy(isSuccessLogin = true) }
        }
        else _uiState.update { it.copy(isSuccessLogin = false) }
    }

    private fun verifyAllFieldsAreFilled() {
        if (_uiState.value.name.isNotEmpty() && _uiState.value.pass.isNotEmpty()) {
            _uiState.update { it.copy( allFieldsAreFilled = true) }
        } else {
            _uiState.update { it.copy( allFieldsAreFilled = false) }
        }
    }

}