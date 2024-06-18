package com.permafrost.socialbrewapp.ui.navigation

sealed class ScreenRoute(val route: String) {
    object Login : ScreenRoute("login")
    object Home : ScreenRoute("home")
    object SignIn : ScreenRoute("sign_in")
    object BarHome : ScreenRoute("bar_home")
}
