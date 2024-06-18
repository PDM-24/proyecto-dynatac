package com.permafrost.socialbrewapp.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.permafrost.socialbrewapp.ui.component.BottomNavBar
import com.permafrost.socialbrewapp.ui.component.TopBar
import com.permafrost.socialbrewapp.ui.theme.Black
import com.permafrost.socialbrewapp.ui.theme.Ocher
import com.permafrost.socialbrewapp.ui.theme.White
import com.permafrost.socialbrewapp.ui.viewmodel.DrinksMenuViewModel


@Composable
fun FoodMenuScreen(navController: NavHostController, drinksMenuViewModel: DrinksMenuViewModel) {

    Scaffold(
        topBar = { TopBar(title = "Beerlab") })
    { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Black)
                .verticalScroll(
                    rememberScrollState(),
                )
        ) {

            // Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Bebidas")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Alimentos")
                }
            }

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                drinksMenuViewModel.products.observeAsState(listOf()).value.forEach { product ->
                    ProductCard(
                        imageResourceId = Icons.Default.Build,
                        productName = product.name
                    )
                }

                // Load more button
                Button(
                    onClick = { drinksMenuViewModel.loadMoreProducts() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Load more")
                }
            }
        }
    }
}


@Composable
fun ProductCard(imageResourceId: ImageVector, productName: String) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(imageVector = Icons.Default.Build, contentDescription = null)
            Text(text = productName, textAlign = TextAlign.Center)
        }
    }
}
