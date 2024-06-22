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
        composable(ScreenRoute.Home.route) {
            val homescreenViewModel: HomeScreenViewModel = viewModel()
            HomeScreen(navController = navController, homescreenViewModel = homescreenViewModel)
        }
        composable(ScreenRoute.Selection.route) {
            val selectionViewModel: SelectionViewModel = viewModel()
            SelectionScreen(navController = navController, selectionViewModel = selectionViewModel)
        }
        composable(ScreenRoute.Cuenta.route) {
            val cuentaViewModel: CuentaViewModel = viewModel()
            CuentaScreen(navController = navController, cuentaViewModel = cuentaViewModel)
        }

        composable(ScreenRoute.DrinksMenu.route) {
            val drinksMenuViewModel: DrinksMenuViewModel = viewModel()
            DrinksMenuScreen(navController = navController, drinksMenuViewModel = drinksMenuViewModel)
        }
        composable(ScreenRoute.SignIn.route) {
            val signInViewModel: SignInViewModel = viewModel()
            SignInScreen(navController = navController, signInViewModel = signInViewModel)
        }
        composable("${ScreenRoute.BarHome.route}/{barId}") { backStackEntry ->
            val barId = backStackEntry.arguments?.getString("barId") ?: return@composable
            val barHomeViewModel: BarHomeScreenViewModel = viewModel()
            BarHomeScreen(navController = navController, barhomescreenViewModel = barHomeViewModel)
        }
    }
}
