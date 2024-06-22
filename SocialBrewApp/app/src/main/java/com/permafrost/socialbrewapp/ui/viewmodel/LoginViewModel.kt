package com.permafrost.socialbrewapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.navigation.NavHostController
import com.permafrost.socialbrewapp.ui.navigation.ScreenRoute
import android.content.Context
import android.util.Log
import com.permafrost.socialbrewapp.data.api.ApiClient
import com.permafrost.socialbrewapp.data.api.LoginRequest
import com.permafrost.socialbrewapp.util.ToastHelper
import retrofit2.HttpException
import java.io.IOException

class LoginViewModel : ViewModel() {

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> = _isLoggedIn

    private val _loginStatusMessage = mutableStateOf("")
    val loginStatusMessage: State<String> = _loginStatusMessage

    private val _userRole = mutableStateOf("")
    val userRole: State<String> = _userRole

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onLoginClick(navController: NavHostController, context: Context) {
        viewModelScope.launch {
            try {
                val response =
                    ApiClient.apiService.login(LoginRequest(_email.value, _password.value))
                if (response.isSuccessful && response.body() != null) {
                    val responseBody = response.body()!!
                    val token = responseBody.token
                    val roles = responseBody.role
                    _isLoggedIn.value = true
                    _userRole.value = roles.firstOrNull() ?: ""
                    _loginStatusMessage.value = "¡Inicio de sesión exitoso!"
                    ToastHelper.showToast(context, _loginStatusMessage.value)
                    if (_userRole.value == "Usuario") {
                        navController.navigate(ScreenRoute.Selection.route)
                    } else if (_userRole.value == "Bar") {
                        navController.navigate(ScreenRoute.BarHome.route)
                    }
                    Log.d("LoginViewModel", "Token: $token")
                } else {
                    _isLoggedIn.value = false
                    _loginStatusMessage.value =
                        "Credenciales incorrectas. Por favor, inténtalo de nuevo."
                    ToastHelper.showToast(context, _loginStatusMessage.value)
                }
            } catch (e: IOException) {
                Log.e("LoginViewModel", "Error de red: ${e.message}")
                _loginStatusMessage.value = "Error de red. Por favor, inténtalo de nuevo."
                ToastHelper.showToast(context, _loginStatusMessage.value)
            } catch (e: HttpException) {
                _loginStatusMessage.value = "Error del servidor. Por favor, inténtalo de nuevo."
                ToastHelper.showToast(context, _loginStatusMessage.value)
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error inesperado: ${e.message}")
                _loginStatusMessage.value =
                    "Error inesperado: Por favor, inténtalo de nuevo."
                ToastHelper.showToast(context, _loginStatusMessage.value)
            }
        }
    }
}



