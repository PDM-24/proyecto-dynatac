package com.permafrost.socialbrewapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.permafrost.socialbrewapp.ui.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(navController: NavHostController, homescreenViewModel: HomeScreenViewModel) {
    Column {
        Text(text = "Bienvenido a Home Screen")

    }
}