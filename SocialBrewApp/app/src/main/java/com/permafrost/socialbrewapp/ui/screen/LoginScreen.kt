package com.permafrost.socialbrewapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.ui.navigation.ScreenRoute
import com.permafrost.socialbrewapp.ui.theme.fontFamily
import com.permafrost.socialbrewapp.ui.viewmodel.LoginViewModel


@Composable
fun LoginScreen(navController: NavHostController, loginViewModel: LoginViewModel = viewModel()) {
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
                    text = "SocialBrew",
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
                    .weight(1.2f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MailTextField(
                    email = loginViewModel.email.value,
                    onEmailChange = { loginViewModel.onEmailChange(it) }
                )
                PasswordTextField(
                    password = loginViewModel.password.value,
                    onPasswordChange = { loginViewModel.onPasswordChange(it) }
                )
                BtnLogin(
                    onClick = { loginViewModel.onLoginClick(navController, context) },
                    enabled = loginViewModel.email.value.isNotEmpty() && loginViewModel.password.value.isNotEmpty()
                )
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
                    text = "¿Aún no tienes una cuenta?",
                    color = colorResource(id = R.color.white),
                    fontSize = 22.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                )
                Row {
                    Text(
                        text = "Registrate",
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
                            navController.navigate(ScreenRoute.SignIn.route)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun MailTextField(email: String, onEmailChange: (String) -> Unit) {
    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            unfocusedTextColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedTextColor = Color.White,
            focusedLabelColor = Color.White,
        ),
        value = email,
        onValueChange = onEmailChange,
        label = {
            Text(
                "Correo",
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        modifier = Modifier.padding(bottom = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        textStyle = TextStyle(fontSize = 22.sp, fontFamily = fontFamily),
    )
}

@Composable
fun PasswordTextField(password: String, onPasswordChange: (String) -> Unit) {
    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            unfocusedTextColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedTextColor = Color.White,
            focusedLabelColor = Color.White,
        ),
        value = password,
        onValueChange = onPasswordChange,
        label = {
            Text(
                "Contraseña",
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.padding(bottom = 8.dp),
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        textStyle = TextStyle(fontSize = 22.sp, fontFamily = fontFamily),
    )
}

@Composable
fun BtnLogin(onClick: () -> Unit, enabled: Boolean) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .height(80.dp)
            .width(280.dp)
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) colorResource(id = R.color.primary_red) else colorResource(
                id = R.color.disabled_gray
            )
        )


    ) {
        Text(
            text = "INICIAR",
            style = TextStyle(
                fontFamily = fontFamily,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            ),
            color = if (enabled) Color.White else colorResource(id = R.color.disabled_gray),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    LoginScreen(navController = rememberNavController(), loginViewModel = LoginViewModel())
}