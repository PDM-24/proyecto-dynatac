package com.permafrost.socialbrewapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
<<<<<<< HEAD
import com.permafrost.socialbrewapp.ui.screen.DrinksMenuScreen
import com.permafrost.socialbrewapp.ui.screen.HomeScreen
import com.permafrost.socialbrewapp.ui.screen.LoginScreen
import com.permafrost.socialbrewapp.ui.viewmodel.DrinksMenuViewModel
=======
import com.permafrost.socialbrewapp.ui.screen.BarHomeScreen
import com.permafrost.socialbrewapp.ui.screen.HomeScreen
import com.permafrost.socialbrewapp.ui.screen.LoginScreen
import com.permafrost.socialbrewapp.ui.screen.SignInScreen
import com.permafrost.socialbrewapp.ui.viewmodel.BarHomeScreenViewModel
>>>>>>> 277e7a2155c55f80a8eeda3d8a31b5b18a012fe8
import com.permafrost.socialbrewapp.ui.viewmodel.HomeScreenViewModel
import com.permafrost.socialbrewapp.ui.viewmodel.LoginViewModel
import com.permafrost.socialbrewapp.ui.viewmodel.SignInViewModel

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
            val homescreenViewModel: HomeScreenViewModel = viewModel ()
            HomeScreen(navController = navController, homescreenViewModel = homescreenViewModel)
        }

<<<<<<< HEAD
        composable(ScreenRoute.DrinksMenu.route) {
            val drinksMenuViewModel: DrinksMenuViewModel = viewModel ()
            DrinksMenuScreen( navController = navController, drinksMenuViewModel = drinksMenuViewModel)
        }

=======
        composable(ScreenRoute.SignIn.route) {
            val signInViewModel: SignInViewModel = viewModel ()
            SignInScreen(navController = navController, signInViewModel = signInViewModel)
        }

        composable(ScreenRoute.BarHome.route) {
            val barhomescreenViewModel: BarHomeScreenViewModel = viewModel ()
            BarHomeScreen(navController = navController, barhomescreenViewModel = barhomescreenViewModel)
        }


>>>>>>> 277e7a2155c55f80a8eeda3d8a31b5b18a012fe8
    }
}

