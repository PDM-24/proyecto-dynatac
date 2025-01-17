package com.permafrost.socialbrewapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.permafrost.socialbrewapp.data.api.ProductsApiWithStringUser
import com.permafrost.socialbrewapp.ui.component.BottomNavBar

import com.permafrost.socialbrewapp.ui.component.TopBar
import com.permafrost.socialbrewapp.ui.navigation.ScreenRoute
import com.permafrost.socialbrewapp.ui.theme.Black
import com.permafrost.socialbrewapp.ui.theme.fontFamily
import com.permafrost.socialbrewapp.ui.viewmodel.DrinksMenuViewModel

@Composable
fun DrinksMenuScreen(barId: String, navController: NavHostController, drinksMenuViewModel: DrinksMenuViewModel = viewModel()) {
    LaunchedEffect(barId) {
        drinksMenuViewModel.loadProductsForBar(barId)
    }

    Scaffold(
        topBar = {
            TopBar(title = "SocialBrew")
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Black)
                .verticalScroll(rememberScrollState())
        ) {

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val products by drinksMenuViewModel.products.collectAsState()


            /*  LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
                ){
                products.forEach { product ->
                    ProductCard(
                        product = product,
                        onClick = { navController.navigate("${ScreenRoute.CommentRating.route}/${product.id}") }
                    )
                }
            }*/


              /*  products.forEach { product ->
                    ProductCard(
                        product = product,
                        onClick = { navController.navigate("${ScreenRoute.CommentRating.route}/${product.id}") }
                    )
                }*/
                products.chunked(2).forEach { productPair ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        productPair.forEach { product ->
                            ProductCard(
                                product = product,
                                onClick = { navController.navigate("${ScreenRoute.CommentRating.route}/${product.id}") }
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(2.dp))
                }

            }
        }
    }
}

@Composable
private fun ProductCard(product: ProductsApiWithStringUser, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .clickable(onClick = onClick),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = product.name, fontFamily = fontFamily, textAlign = TextAlign.Center, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "$${product.price}", textAlign = TextAlign.Center, fontFamily = fontFamily, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}
