package com.permafrost.socialbrewapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.permafrost.socialbrewapp.ui.screen.*
import com.permafrost.socialbrewapp.ui.viewmodel.*
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Login.route
    ) {
        composable(ScreenRoute.Login.route) {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }
        composable(ScreenRoute.Selection.route) {
            val selectionViewModel: SelectionViewModel = viewModel()
            SelectionScreen(navController = navController, selectionViewModel = selectionViewModel)
        }
        composable(ScreenRoute.Cuenta.route) {
            val cuentaViewModel: CuentaViewModel = viewModel()
            CuentaScreen(navController = navController, cuentaViewModel = cuentaViewModel)
        }
        composable("${ScreenRoute.DrinksMenu.route}/{barId}") { backStackEntry ->
            val barId = backStackEntry.arguments?.getString("barId") ?: return@composable
            val drinksMenuViewModel: DrinksMenuViewModel = viewModel()
            DrinksMenuScreen(barId = barId, navController = navController, drinksMenuViewModel = drinksMenuViewModel)
        }
        composable(ScreenRoute.SignIn.route) {
            val signInViewModel: SignInViewModel = viewModel()
            SignInScreen(navController = navController, signInViewModel = signInViewModel)
        }
        composable("${ScreenRoute.BarManagement.route}/{barId}") { backStackEntry ->
            val barId = backStackEntry.arguments?.getString("barId") ?: return@composable
            val barManagementViewModel: BarManagementViewModel = viewModel()
            BarManagementScreen(navController = navController, barId = barId, barManagementViewModel = barManagementViewModel)
        }
    }
}




