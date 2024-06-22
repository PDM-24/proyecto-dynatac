package com.permafrost.socialbrewapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.ui.component.BottomNavBar
import com.permafrost.socialbrewapp.ui.component.TopBarCuenta
import com.permafrost.socialbrewapp.ui.viewmodel.RenameViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RenameScreen(navController: NavHostController, renameViewModel: RenameViewModel = viewModel()) {
    val context = LocalContext.current
    var newName by remember { mutableStateOf(TextFieldValue("")) }


    Scaffold(
        topBar = {
            TopBarCuenta(title = "Cambiar nombre", onBackClick = { navController.popBackStack() })
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        },
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Escribe tu nuevo nombre:",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )
                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    placeholder = {
                        Text(
                            "Ej. Cristiano Ronaldo",
                            style = TextStyle(
                                color = Color.Gray
                            )
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray,
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White,
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(0.85f)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        // Handle the confirm action
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_red))
                ) {
                    Text(
                        modifier = Modifier.padding(2.dp, 0.dp),
                        text = "CONFIRMAR",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}
