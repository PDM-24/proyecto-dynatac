package com.permafrost.socialbrewapp.ui.navigation

sealed class ScreenRoute(val route: String) {
    object Login : ScreenRoute("login")
    object Selection : ScreenRoute("selection")
    object Cuenta : ScreenRoute("cuenta")
    object DrinksMenu : ScreenRoute("drinksmenu")
    object SignIn : ScreenRoute("sign_in")
    object BarManagement : ScreenRoute("bar_management")
    object Rename : ScreenRoute("rename")
    object Changepass : ScreenRoute("changepass")
    object Creditos : ScreenRoute("creditos")
    object CommentRating : ScreenRoute("comment_rating")

}

