package com.permafrost.socialbrewapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.data.api.NewProductRequest
import com.permafrost.socialbrewapp.data.api.ProductsApiWithStringUser
import com.permafrost.socialbrewapp.ui.component.BottomNavBar
import com.permafrost.socialbrewapp.ui.component.TopBar
import com.permafrost.socialbrewapp.ui.theme.Black
import com.permafrost.socialbrewapp.ui.theme.fontFamily
import com.permafrost.socialbrewapp.ui.viewmodel.BarManagementViewModel
import com.permafrost.socialbrewapp.util.ToastHelper


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
    val context = LocalContext.current

    LaunchedEffect(barId) {
        barManagementViewModel.loadProductsForBar(barId)
    }

    Scaffold(
        topBar = { TopBar(title = "Administración de bar") },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = colorResource(id = R.color.primary_red)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar producto", tint = colorResource(
                    id = R.color.white
                ))
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(colorResource(id = R.color.background))
        ) {
           /* Button(onClick = { showDialog = true }) {
                Text("Agregar producto",
                    fontFamily = fontFamily)
            }*/

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Agregar producto") },
                    text = {
                        Column {
                            TextField(
                                value = newProductName,
                                onValueChange = { newProductName = it },
                                label = { Text("Nombre",
                                    fontFamily = fontFamily) }
                            )
                            TextField(
                                value = newProductPrice,
                                onValueChange = { newProductPrice = it },
                                label = { Text("Precio",
                                    fontFamily = fontFamily) },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                            )
                            TextField(
                                value = newProductCategory,
                                onValueChange = { newProductCategory = it },
                                label = { Text("Categoría",
                                    fontFamily = fontFamily) }
                            )
                            TextField(
                                value = newProductImage,
                                onValueChange = { newProductImage = it },
                                label = { Text("URL",
                                    fontFamily = fontFamily) }
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
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_red))
                        ) {
                            Text("Agregar",
                                fontFamily = fontFamily)
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("Cancelar",
                                fontFamily = fontFamily)
                        }
                    }
                )
            }

            LazyColumn {
                items(products) { product ->
                    ProductItem(
                        product = product,
                        onClick = {
                        },
                        onDeleteClick = {
                            barManagementViewModel.deleteProduct(product.id)
                            ToastHelper.showToast(context, "Producto Eliminado")
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
            .padding(28.dp, 8.dp),

        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth()
            ) {

                Text(text = product.name, fontSize = 28.sp, color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = fontFamily, fontWeight = FontWeight.Bold)
                Text(text = "$ ${product.price}", fontSize = 24.sp,
                    fontFamily = fontFamily, fontWeight = FontWeight.Bold , color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            }

            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Crop
            )

            onDeleteClick?.let {
                IconButton(onClick = it) {
                    Icon( modifier = Modifier.size(35.dp) ,imageVector = Icons.Default.Delete, contentDescription = "Borrar")
                }
            }
        }
    }
}

@Composable
@Preview
fun ProductItemPreview() {
    ProductItem(
        product = ProductsApiWithStringUser(
            id = "1",
            name = "Cerveza",
            price = 2.5,
            category = "Bebida",
            image = "https://www.cervezasyria.com/wp-content/uploads/2020/06/cerveza-syria-1.jpg",
            user = "1"
        ),
        onClick = {},
        onDeleteClick = {}
    )
}