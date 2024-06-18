package com.permafrost.socialbrewapp.ui.component

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.ui.navigation.Navigation
import com.permafrost.socialbrewapp.ui.navigation.ScreenRoute
import com.permafrost.socialbrewapp.ui.screen.DrinksMenuScreen
import com.permafrost.socialbrewapp.ui.screen.HomeScreen
import com.permafrost.socialbrewapp.ui.theme.Black
import com.permafrost.socialbrewapp.ui.theme.SlightGray
import com.permafrost.socialbrewapp.ui.theme.White
import com.permafrost.socialbrewapp.ui.viewmodel.DrinksMenuViewModel
import com.permafrost.socialbrewapp.ui.viewmodel.HomeScreenViewModel

@Composable
fun BottomNavBar() {
    val navigationController = rememberNavController()
    val context = LocalContext.current
    val selectedIcon = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = SlightGray
            ) {
                IconButton(onClick = {
                    selectedIcon.value = Icons.Default.Home
                    navigationController.navigate(ScreenRoute.Home.route){
                        popUpTo(0)
                    }
                    Toast.makeText(context, "Ha ingresado a la pantalla Principal", Toast.LENGTH_SHORT).show()
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_icon),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selectedIcon.value == Icons.Default.Home) Black else Color.White
                    )
                }

                IconButton(onClick = {
                     selectedIcon.value = Icons.Default.Favorite //screen mostrando los favoritos
                     navigationController.navigate(ScreenRoute.DrinksMenu.route) //falta agregar la pantalla de Favorites a mi proyecto/codigo
                     {
                         popUpTo(0)
                     }
                     Toast.makeText(context, "Ha ingresado a la pantalla de Favoritos", Toast.LENGTH_SHORT).show()
                 }, modifier = Modifier.weight(1f)) {
                     Icon(Icons.Default.Favorite, contentDescription = null, modifier = Modifier.size(26.dp),
                         tint = if (selectedIcon.value == Icons.Default.Favorite) Black else White )
                 }
                IconButton(onClick = {
                    selectedIcon.value = Icons.Default.Menu //la screen mostrando el menu del restaurante seleccionado
                    navigationController.navigate(ScreenRoute.DrinksMenu.route){
                        popUpTo(0)
                    }
                    Toast.makeText(
                        context,
                        "Ha ingresado a la pantalla de Menu",
                        Toast.LENGTH_SHORT
                    ).show()
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        painter = painterResource(id = R.drawable.beer),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selectedIcon.value == Icons.Default.Menu) Black else White
                    )
                }
                 IconButton(onClick = {
                     selectedIcon.value = Icons.Default.Person //screen mostrando los favoritos
                     //cambiar .Home.route por .Profile.route o el nombre de la screen del perfil del usuario
                     navigationController.navigate(ScreenRoute.Home.route) //falta agregar la pantalla del perfil a mi proyecto/codigo
                     {
                         popUpTo(0)
                     }
                     Toast.makeText(context, "Ha ingresado a la pantalla de su Usuario", Toast.LENGTH_SHORT).show()
                 }, modifier = Modifier.weight(1f)) {
                     Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(26.dp),
                         tint = if (selectedIcon.value == Icons.Default.Person) Black else White)
                 }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = ScreenRoute.Home.route,
            modifier = Modifier.padding(paddingValues)
        )
        {
            composable(ScreenRoute.Home.route) {
                HomeScreen(
                    navController = navigationController,
                    homescreenViewModel = HomeScreenViewModel()
                )
            }
            composable(ScreenRoute.DrinksMenu.route) {
                DrinksMenuScreen(
                    navController = navigationController,
                    drinksMenuViewModel = DrinksMenuViewModel()
                )
            }

           /* composable(ScreenRoute.Favorite.route) {
                DrinksMenuScreen(
                    navController = navigationController,
                    favoriteMenuViewModel = favoriteMenuViewModel() //cambiar a ruta de la siguiente pantalla
                )
            }
            composable(ScreenRoute.Favorite.route) {
                DrinksMenuScreen(
                    navController = navigationController,
                    favoriteMenuViewModel = FavoriteMenuViewModel() //cambiar a ruta de la pantalla restante
                )
            }*/

        }

    }

}
/*
@Composable
fun loadVectorResource(context: Context, @DrawableRes resId: Int): ImageVector {
    val drawable = ImageVector.vectorResource(id = resId).create(context.resources, resId, context.theme)
    requireNotNull(drawable) { "Resource not found: $resId" }
    return ImageVector.vectorResource(drawable)
}

fun Drawable.toImageVector(): ImageVector {
    val drawable = DrawableCompat.wrap(this).mutate()
    val vectorDrawable = drawable as ImageVector.vectorResource
    return ImageVector.vectorResource(vectorDrawable)
}*/
