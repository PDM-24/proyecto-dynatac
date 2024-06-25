package com.permafrost.socialbrewapp.ui.screen


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
import androidx.navigation.NavHostController
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.ui.component.TopBar
import com.permafrost.socialbrewapp.ui.viewmodel.CuentaViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.permafrost.socialbrewapp.ui.component.BottomNavBar
import com.permafrost.socialbrewapp.ui.navigation.ScreenRoute
import com.permafrost.socialbrewapp.ui.theme.fontFamily


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CuentaScreen(navController: NavHostController, cuentaViewModel: CuentaViewModel = viewModel()) {
    val context = LocalContext.current


    Scaffold(
        topBar = {
            TopBar(title = "Cuenta")
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
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Text(
                    text = "SocialBrew",
                    color = Color.White,
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))


                Image(
                    painter = painterResource(id = R.drawable.beer),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(100.dp)
                )


                Spacer(modifier = Modifier.height(55.dp))



                Button(
                    onClick = { navController.navigate("Creditos") },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.navbar_gray)),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                      //  .padding(vertical = 8.dp)
                        //.height(48.dp)
                        .height(80.dp)
                        .width(280.dp)
                        .padding(top = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.list),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Text("Créditos", fontSize = 25.sp,fontFamily = fontFamily, color = Color.White, modifier = Modifier.weight(1f))
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate(route = ScreenRoute.Login.route){popUpTo(ScreenRoute.Login.route){inclusive = true} } },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_red)),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                      //  .padding(vertical = 8.dp)
                     //   .height(48.dp)
                        .height(80.dp)
                        .width(280.dp)
                        .padding(top = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.logout),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text("Cerrar sesión", fontSize = 25.sp, fontFamily = fontFamily, color = Color.White, modifier = Modifier.weight(1f))
                    }
                }

                Spacer(modifier = Modifier.height(70.dp))
            }
        }
    }
}

@Composable
@Preview
fun CuentaScreenPreview() {
    CuentaScreen(navController = NavHostController(LocalContext.current))
}
