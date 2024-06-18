package com.permafrost.socialbrewapp.ui.navigation

sealed class ScreenRoute(val route: String) {
    object Login : ScreenRoute("login")
    object Home : ScreenRoute("home")
<<<<<<< HEAD
    object DrinksMenu : ScreenRoute("drinksmenu")
    object FoodsMenu : ScreenRoute("drinksmenu")
=======
    object SignIn : ScreenRoute("sign_in")
    object BarHome : ScreenRoute("bar_home")
>>>>>>> 277e7a2155c55f80a8eeda3d8a31b5b18a012fe8
}
