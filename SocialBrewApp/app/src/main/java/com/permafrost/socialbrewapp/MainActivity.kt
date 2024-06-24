package com.permafrost.socialbrewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.permafrost.socialbrewapp.ui.component.BottomNavBar
import com.permafrost.socialbrewapp.ui.navigation.Navigation
import com.permafrost.socialbrewapp.ui.screen.ChangepassScreen
import com.permafrost.socialbrewapp.ui.screen.CommentRatingScreen
import com.permafrost.socialbrewapp.ui.screen.CreditosScreen
import com.permafrost.socialbrewapp.ui.screen.CuentaScreen
import com.permafrost.socialbrewapp.ui.screen.DrinksMenuScreen
import com.permafrost.socialbrewapp.ui.screen.RenameScreen
import com.permafrost.socialbrewapp.ui.screen.SelectionScreen
import com.permafrost.socialbrewapp.ui.theme.SocialBrewAppTheme
import com.permafrost.socialbrewapp.ui.viewmodel.DrinksMenuViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SocialBrewAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                  //  Navigation(navController)
                    CommentRatingScreen()
                }
            }
        }
    }
}
