package com.permafrost.socialbrewapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.permafrost.socialbrewapp.data.api.ApiClient
import com.permafrost.socialbrewapp.data.api.ProductsApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DrinksMenuViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<ProductsApi>>(emptyList())
    val products: StateFlow<List<ProductsApi>> = _products

    fun loadProductsForBar(barId: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.findAllProducts()
                val newProducts = response.filter { it.category == "Bebida" }
                _products.value = newProducts
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

    fun loadMoreProducts(barId: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.findAllProducts()
                val newProducts = response.filter { it.category == "Bebida" }
                _products.value = _products.value.union(newProducts).toList()
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }
}
