import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.ui.navigation.ScreenRoute
import com.permafrost.socialbrewapp.ui.screen.MailTextField
import com.permafrost.socialbrewapp.ui.screen.PasswordTextField
import com.permafrost.socialbrewapp.ui.theme.fontFamily

@Composable
fun SignInScreen(navController: NavHostController, signInViewModel: SignInViewModel = viewModel()) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Registrarse",
                    color = Color.White,
                    fontSize = 45.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(48.dp))

                Image(
                    painter = painterResource(R.drawable.beer),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(100.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1.8f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NameTextField(
                    name = signInViewModel.name.value,
                    onNameChange = { signInViewModel.onNameChange(it) }
                )
                MailTextField(
                    email = signInViewModel.email.value,
                    onEmailChange = { signInViewModel.onEmailChange(it) }
                )
                PasswordTextField(
                    password = signInViewModel.password.value,
                    onPasswordChange = { signInViewModel.onPasswordChange(it) }
                )

                BtnSignIn(onClick = { signInViewModel.onSignInClick(navController, context) })
            }

            Column(
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "¿Ya tienes una cuenta?",
                    color = colorResource(id = R.color.white),
                    fontSize = 22.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                )
                Row {
                    Text(
                        text = "Inicia sesión",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal
                    )
                    Text(text = "  ")   
                    Text(
                        text = "aquí",
                        color = colorResource(id = R.color.second_red),
                        fontSize = 22.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            navController.navigate(ScreenRoute.Login.route)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NameTextField(name: String, onNameChange: (String) -> Unit) {
    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            unfocusedTextColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedTextColor = Color.White,
            focusedLabelColor = Color.White,
        ),
        value = name,
        onValueChange = onNameChange,
        label = {
            Text(
                "Nombre",
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        textStyle = TextStyle(fontSize = 22.sp, fontFamily = fontFamily),
    )
}

@Composable
fun BtnSignIn(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(vertical = 5.dp)
            .height(60.dp)
            .width(280.dp)
            .clip(RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_red)),
    ) {
        Text(
            text = "REGISTRATE",
            style = TextStyle(
                fontFamily = fontFamily,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
@Preview
fun SignInScreenPreview() {
    SignInScreen(navController = rememberNavController())
}