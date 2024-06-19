package com.permafrost.socialbrewapp.ui.viewmodel


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DrinksMenuViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    init {
        _products.value = listOf(
            Product("COCA-COLA 354ml", Icons.Default.Build),
            Product("COCA-COLA LATA", Icons.Default.Build),
            Product("CRISTAL 600ml", Icons.Default.Build),
            Product("PILSENER 330ml", Icons.Default.Build),
            Product("SUPREMA 330ml", Icons.Default.Build),
            Product("CORONA 330ml", Icons.Default.Build),

            )
    }

    fun loadMoreProducts() {
        val newProducts = listOf(
            Product("NEW PRODUCT 1", Icons.Default.Build),
            Product("NEW PRODUCT 2", Icons.Default.Build),

            )
        _products.value = _products.value?.plus(newProducts)
    }
}

data class Product(val name: String, val imageResourceId: ImageVector) {

}