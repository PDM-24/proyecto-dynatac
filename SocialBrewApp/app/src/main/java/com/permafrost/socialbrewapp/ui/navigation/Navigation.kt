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
        composable(ScreenRoute.Selection.route + "?fromBeer={fromBeer}") { backStackEntry ->
            val fromBeer = backStackEntry.arguments?.getString("fromBeer")?.toBoolean() ?: false
            val selectionViewModel: SelectionViewModel = viewModel()
            SelectionScreen(navController = navController, selectionViewModel = selectionViewModel, fromBeer = fromBeer)
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
        composable(ScreenRoute.Rename.route) {
            val renameViewModel: RenameViewModel = viewModel()
            RenameScreen(navController = navController, renameViewModel = renameViewModel)
        }
        composable(ScreenRoute.Changepass.route) {
            val changepassViewModel: ChangepassViewModel = viewModel()
            ChangepassScreen(navController = navController, changepassViewModel = changepassViewModel)
        }
        composable(ScreenRoute.Creditos.route) {
            val creditosViewModel: CreditosViewModel = viewModel()
            CreditosScreen(navController = navController, creditosViewModel = creditosViewModel)
        }
        composable("${ScreenRoute.CommentRating.route}/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            val commentViewModel: CommentViewModel = viewModel()
            val productDetailViewModel: ProductDetailViewModel = viewModel()
            CommentRatingScreen(
                productId = productId,
                navController = navController,
                commentViewModel = commentViewModel,
                productDetailViewModel = productDetailViewModel
            )
        }
    }
}