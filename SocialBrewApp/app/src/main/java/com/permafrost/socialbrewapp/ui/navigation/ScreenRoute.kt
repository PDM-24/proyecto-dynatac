package com.permafrost.socialbrewapp.ui.navigation

sealed class ScreenRoute(val route: String) {
    object Login : ScreenRoute("login")
    object Selection : ScreenRoute("selection")
    object Cuenta : ScreenRoute("cuenta")
    object DrinksMenu : ScreenRoute("drinksmenu")
    object SignIn : ScreenRoute("sign_in")
    object BarHome : ScreenRoute("bar_home")
}
