package com.permafrost.socialbrewapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.navigation.NavHostController
import com.permafrost.socialbrewapp.ui.navigation.ScreenRoute
import android.content.Context
import com.permafrost.socialbrewapp.util.ToastHelper

class LoginViewModel : ViewModel() {

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> = _isLoggedIn

    private val _loginStatusMessage = mutableStateOf("")
    val loginStatusMessage: State<String> = _loginStatusMessage

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onLoginClick(navController: NavHostController, context: Context) {
        viewModelScope.launch {
            // Lógica de autenticación que se cambiará más tarde...
            if (_email.value == "prueba@gmail.com" && _password.value == "12345") {
                _isLoggedIn.value = true
                _loginStatusMessage.value = "¡Inicio de sesión exitoso!"
                ToastHelper.showToast(context, _loginStatusMessage.value)
                navController.navigate(ScreenRoute.Home.route)
            } else {
                _isLoggedIn.value = false
                _loginStatusMessage.value = "Credenciales incorrectas. Por favor, inténtalo de nuevo."
                ToastHelper.showToast(context, _loginStatusMessage.value)
            }
        }
    }
}

