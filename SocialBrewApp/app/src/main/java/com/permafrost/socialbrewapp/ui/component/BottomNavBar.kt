package com.permafrost.socialbrewapp.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.ui.navigation.ScreenRoute

@Composable
fun BottomNavBar(navController: NavHostController) {

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    BottomAppBar(
        containerColor = colorResource(id = R.color.navbar_gray)
    ) {
        IconButton(
            onClick = {
            navController.navigate(ScreenRoute.DrinksMenu.route) {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }


        }, modifier = Modifier.weight(1f),
            enabled = currentRoute != ScreenRoute.DrinksMenu.route

        )

        {
            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                tint = if (currentRoute == ScreenRoute.DrinksMenu.route) Color.White else Color.Black
            )
        }
        IconButton(onClick = {
            navController.navigate(ScreenRoute.Selection.route) {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }


        }, modifier = Modifier.weight(1f)) {
            Icon(
                painter = painterResource(id = R.drawable.beer),
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                tint = if (currentRoute == ScreenRoute.Selection.route) Color.White else Color.Black
            )
        }

        IconButton(onClick = {
            navController.navigate(ScreenRoute.Cuenta.route) {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }



        }, modifier = Modifier.weight(1f)) {
            Icon(
                Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                tint = if (currentRoute == ScreenRoute.Cuenta.route) Color.White else Color.Black
            )
        }
    }
}