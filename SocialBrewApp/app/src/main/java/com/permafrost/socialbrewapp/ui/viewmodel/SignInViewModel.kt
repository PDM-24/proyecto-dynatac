package com.permafrost.socialbrewapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController
import android.content.Context
import android.util.Log
import com.permafrost.socialbrewapp.data.api.ApiClient
import com.permafrost.socialbrewapp.data.api.NewBarRequest
import com.permafrost.socialbrewapp.data.api.RegisterRequest
import com.permafrost.socialbrewapp.ui.navigation.ScreenRoute
import com.permafrost.socialbrewapp.util.ToastHelper
import retrofit2.HttpException
import java.io.IOException

class SignInViewModel : ViewModel() {

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _role = mutableStateOf("Cliente")
    val role: State<String> = _role

    private val _isSignedIn = mutableStateOf(false)
    val isSignedIn: State<Boolean> = _isSignedIn

    private val _signInStatusMessage = mutableStateOf("")
    val signInStatusMessage: State<String> = _signInStatusMessage

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onRoleChange(newRole: String) {
        _role.value = newRole
    }

    fun onSignInClick(navController: NavHostController, context: Context) {
        viewModelScope.launch {
            try {
                if (role.value == "Cliente") {
                    val request = RegisterRequest(name.value, email.value, password.value)
                    val response = ApiClient.apiService.register(request)

                    if (response.isSuccessful && response.body() != null) {
                        _isSignedIn.value = true
                        _signInStatusMessage.value = "¡Registro de cliente exitoso!"
                        ToastHelper.showToast(context, _signInStatusMessage.value)
                        navController.navigate(ScreenRoute.Login.route)
                    } else {
                        _isSignedIn.value = false
                        _signInStatusMessage.value =
                            "Registro de cliente fallido. Por favor, inténtalo de nuevo."
                        ToastHelper.showToast(context, _signInStatusMessage.value)
                    }
                } else {
                    val request = NewBarRequest(name.value, email.value, password.value)
                    val response = ApiClient.apiService.createNewBar(request)

                    if (response.isSuccessful && response.body() != null) {
                        _isSignedIn.value = true
                        _signInStatusMessage.value = "¡Registro de bar exitoso!"
                        ToastHelper.showToast(context, _signInStatusMessage.value)
                        navController.navigate(ScreenRoute.Login.route)
                    } else {
                        _isSignedIn.value = false
                        _signInStatusMessage.value =
                            "Registro de bar fallido. Por favor, inténtalo de nuevo."
                        ToastHelper.showToast(context, _signInStatusMessage.value)
                    }
                }
            } catch (e: IOException) {
                _signInStatusMessage.value = "Error de red. Por favor, inténtalo de nuevo."
                ToastHelper.showToast(context, _signInStatusMessage.value)
            } catch (e: HttpException) {
                _signInStatusMessage.value = "Error del servidor. Por favor, inténtalo de nuevo."
                ToastHelper.showToast(context, _signInStatusMessage.value)
            } catch (e: Exception) {
                _signInStatusMessage.value =
                    "Error inesperado: ${e.message}. Por favor, inténtalo de nuevo."
                ToastHelper.showToast(context, _signInStatusMessage.value)
            }
        }
    }
}
