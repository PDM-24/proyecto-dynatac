package com.permafrost.socialbrewapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.permafrost.socialbrewapp.ui.component.TopBar
import com.permafrost.socialbrewapp.ui.viewmodel.SelectionViewModel
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.ui.navigation.ScreenRoute

@Composable
fun SelectionScreen(navController: NavHostController, selectionViewModel: SelectionViewModel) {
    Scaffold(
        topBar = {
            TopBar(title = "SocialBrew")
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
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Selecciona un bar",
                    color = Color.White,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                BarOptionWithTitle(
                    imageRes = R.drawable.baruno,
                    title = "Bar Malta",
                    onClick = {  }
                )
                Spacer(modifier = Modifier.height(16.dp))

                BarOptionWithTitle(
                    imageRes = R.drawable.baruno,
                    title = "Beerlab",
                    onClick = { }
                )
                Spacer(modifier = Modifier.height(16.dp))

                BarOptionWithTitle(
                    imageRes = R.drawable.baruno,
                    title = "Los tarros",
                    onClick = { }
                )
            }
        }
    )
}

@Composable
fun BarOptionWithTitle(imageRes: Int, title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BarOption(
            imageRes = imageRes,
            onClick = onClick
        )
        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun BarOption(imageRes: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .clickable(onClick = onClick)
            .background(color = colorResource(id = R.color.primary_red), shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
    }
}
