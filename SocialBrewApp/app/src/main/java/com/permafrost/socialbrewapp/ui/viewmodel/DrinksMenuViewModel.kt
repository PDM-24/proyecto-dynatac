package com.permafrost.socialbrewapp.ui.viewmodel

import android.util.Log
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
                Log.d("DrinksMenuViewModel", "Fetching products for barId: $barId")
                val productsResponse = ApiClient.apiService.findProductsFromABar(barId)
                _products.value = productsResponse
                Log.d("DrinksMenuViewModel", "Products: $productsResponse")
            } catch (e: Exception) {
                Log.e("DrinksMenuViewModel", "Error fetching products: ${e.message}")
            }
        }
    }

    fun loadMoreProducts(barId: String) {
        viewModelScope.launch {
            try {
                val productsResponse = ApiClient.apiService.findProductsFromABar(barId)
                _products.value += productsResponse
                Log.d("DrinksMenuViewModel", "More products: $productsResponse")
            } catch (e: Exception) {
                Log.e("DrinksMenuViewModel", "Error loading more products: ${e.message}")
            }
        }
    }
}
