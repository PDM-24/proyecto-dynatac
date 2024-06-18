package com.permafrost.socialbrewapp.ui.navigation

sealed class ScreenRoute(val route: String) {
    object Login : ScreenRoute("login")
    object Home : ScreenRoute("home")
    object DrinksMenu : ScreenRoute("drinksmenu")
    object FoodsMenu : ScreenRoute("drinksmenu")
}
