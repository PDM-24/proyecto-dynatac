import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController
import android.content.Context
import com.permafrost.socialbrewapp.ui.navigation.ScreenRoute
import com.permafrost.socialbrewapp.ui.util.ToastHelper

class SignInViewModel : ViewModel() {

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

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

    fun onSignInClick(navController: NavHostController, context: Context) {
        viewModelScope.launch {
            if (_name.value.isEmpty() || _email.value.isEmpty() || _password.value.isEmpty()) {
                _signInStatusMessage.value = "Todos los campos son obligatorios."
                ToastHelper.showToast(context, _signInStatusMessage.value)
            } else {
                _isSignedIn.value = true
                _signInStatusMessage.value = "¡Registro exitoso!"
                ToastHelper.showToast(context, _signInStatusMessage.value)
                navController.navigate(ScreenRoute.Login.route)
            }
        }
    }
}
