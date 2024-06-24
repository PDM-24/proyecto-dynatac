package com.permafrost.socialbrewapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.permafrost.socialbrewapp.data.api.NewProductRequest
import com.permafrost.socialbrewapp.data.api.ProductsApiWithStringUser
import com.permafrost.socialbrewapp.ui.component.BottomNavBar
import com.permafrost.socialbrewapp.ui.component.TopBar
import com.permafrost.socialbrewapp.ui.viewmodel.BarManagementViewModel


@Composable
fun BarManagementScreen(
    navController: NavHostController,
    barId: String,
    barManagementViewModel: BarManagementViewModel = viewModel()
) {
    val products by barManagementViewModel.products.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var newProductName by remember { mutableStateOf(TextFieldValue()) }
    var newProductPrice by remember { mutableStateOf(TextFieldValue()) }
    var newProductCategory by remember { mutableStateOf(TextFieldValue()) }
    var newProductImage by remember { mutableStateOf(TextFieldValue()) }

    LaunchedEffect(barId) {
        barManagementViewModel.loadProductsForBar(barId)
    }

    Scaffold(
        topBar = { TopBar(title = "Manage Bar") },
        bottomBar = { BottomNavBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Black)
        ) {
            Button(onClick = { showDialog = true }) {
                Text("Add Product")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Add Product") },
                    text = {
                        Column {
                            TextField(
                                value = newProductName,
                                onValueChange = { newProductName = it },
                                label = { Text("Product Name") }
                            )
                            TextField(
                                value = newProductPrice,
                                onValueChange = { newProductPrice = it },
                                label = { Text("Product Price") },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                            )
                            TextField(
                                value = newProductCategory,
                                onValueChange = { newProductCategory = it },
                                label = { Text("Product Category") }
                            )
                            TextField(
                                value = newProductImage,
                                onValueChange = { newProductImage = it },
                                label = { Text("Product Image URL") }
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                val product = NewProductRequest(
                                    name = newProductName.text,
                                    price = newProductPrice.text.toDoubleOrNull() ?: 0.0,
                                    category = newProductCategory.text,
                                    image = newProductImage.text
                                )
                                barManagementViewModel.addProduct(barId, product)
                                showDialog = false
                            }
                        ) {
                            Text("Add")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }

            LazyColumn {
                items(products) { product ->
                    ProductItem(
                        product = product,
                        onClick = {
                            // Handle product click if needed
                        },
                        onDeleteClick = {
                            barManagementViewModel.deleteProduct(product.id)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: ProductsApiWithStringUser, onClick: () -> Unit, onDeleteClick: (() -> Unit)?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(product.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(4.dp),
                    contentScale = ContentScale.Crop
                )
                Text(text = product.name, fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
                Text(text = "${product.price} $", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            }
            onDeleteClick?.let {
                IconButton(onClick = it) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}