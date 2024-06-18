package com.permafrost.socialbrewapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.permafrost.socialbrewapp.ui.viewmodel.BarHomeScreenViewModel


@Composable
fun BarHomeScreen(navController: NavHostController, barhomescreenViewModel: BarHomeScreenViewModel) {
    Column {
        Text(text = "Bienvenido a Bar Screen")

    }
}