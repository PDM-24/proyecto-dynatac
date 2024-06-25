package com.permafrost.socialbrewapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.permafrost.socialbrewapp.ui.component.BottomNavBar
import com.permafrost.socialbrewapp.ui.component.TopBar
import com.permafrost.socialbrewapp.ui.navigation.ScreenRoute
import com.permafrost.socialbrewapp.ui.viewmodel.SelectionViewModel
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.ui.component.TopBarCuenta
import com.permafrost.socialbrewapp.ui.theme.fontFamily

@Composable
fun SelectionScreen(navController: NavHostController, selectionViewModel: SelectionViewModel = viewModel(), fromBeer: Boolean) {
    val barAccounts by selectionViewModel.barAccounts.collectAsState()

    Scaffold(
        topBar = {
            if (fromBeer) {
                TopBarCuenta(title = "Selecciona un bar", onBackClick = { navController.popBackStack() })
            } else {
                TopBar(title = "SocialBrew")
            }
        },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Selecciona un bar",
                    fontFamily = fontFamily,
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn {
                    items(barAccounts) { bar ->
                        BarOptionWithTitle(
                            title = bar.username,
                            onClick = { navController.navigate("${ScreenRoute.DrinksMenu.route}/${bar.id}")
                            {
                                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            }}

                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    )
}

@Composable
fun BarOptionWithTitle(title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BarOption(
            onClick = onClick
        )
        Text(
            text = title,
            color = Color.White,
            fontFamily = fontFamily,
            fontSize = 25.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun BarOption(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .clickable(onClick = onClick)
            .background(color = colorResource(id = R.color.primary_red), shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.beer),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(Color.White)

        )
    }
}