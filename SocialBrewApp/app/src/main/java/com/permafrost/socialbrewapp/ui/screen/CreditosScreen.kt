package com.permafrost.socialbrewapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.ui.component.BottomNavBar
import com.permafrost.socialbrewapp.ui.component.TopBarCuenta
import com.permafrost.socialbrewapp.ui.theme.fontFamily
import com.permafrost.socialbrewapp.ui.viewmodel.CreditosViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreditosScreen(
    navController: NavHostController,
    creditosViewModel: CreditosViewModel = viewModel()
) {
    val context = LocalContext.current


    Scaffold(
        topBar = {
            TopBarCuenta(title = "Créditos", onBackClick = { navController.popBackStack() })
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
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.beer),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(100.dp)
                )


                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "SocialBrew",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold
                )


                Spacer(modifier = Modifier.height(32.dp))


                Box(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(1.dp) // Margen blanco
                ) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.background)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "SocialBrew es una aplicación desarrollada por cinco estudiantes de la Universidad Centroamericana José Simeón Cañas",
                                color = Color.White,
                                fontSize = 23.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center
                            )


                            Spacer(modifier = Modifier.height(16.dp))


                            val developers = listOf(
                                "Oscar Ayala (Frontend)",
                                "Sofía Ramirez (Frontend)",
                                "Alberto Mijango (Backend)",
                                "Miguel Campos (Backend)",
                                "Pablo Castellón (Frontend)"
                            )
                            developers.forEach { developer ->
                                Text(
                                    text = developer,
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CreditosPreview() {
    CreditosScreen(navController = NavHostController(LocalContext.current))
}