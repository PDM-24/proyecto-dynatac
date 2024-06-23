package com.permafrost.socialbrewapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.permafrost.socialbrewapp.data.api.ApiClient
import com.permafrost.socialbrewapp.data.api.NewProductRequest
import com.permafrost.socialbrewapp.data.api.ProductsApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BarManagementViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<ProductsApi>>(emptyList())
    val products: StateFlow<List<ProductsApi>> = _products

    fun loadProductsForBar(barId: String) {
        viewModelScope.launch {
            try {
                Log.d("BarManagementViewModel", "Fetching products for barId: $barId")
                val productsResponse = ApiClient.apiService.findProductsFromABar(barId)
                _products.value = productsResponse
                Log.d("BarManagementViewModel", "Products: $productsResponse")
            } catch (e: Exception) {
                Log.e("BarManagementViewModel", "Error fetching products: ${e.message}")
            }
        }
    }

    fun addProduct(barId: String, newProduct: NewProductRequest) {
        viewModelScope.launch {
            try {
                Log.d("BarManagementViewModel", "Adding product: $newProduct to barId: $barId")
                val addedProduct = ApiClient.apiService.createNewProduct(barId, newProduct)
                _products.value += addedProduct
                Log.d("BarManagementViewModel", "Added product: $addedProduct")
            } catch (e: Exception) {
                Log.e("BarManagementViewModel", "Error adding product: ${e.message}")
            }
        }
    }

    fun deleteProduct(productId: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.deleteProduct(productId)
                if (response.isSuccessful) {
                    _products.value = _products.value.filter { it.id != productId }
                }
            } catch (e: Exception) {
                Log.e("BarManagementViewModel", "Error deleting product: ${e.message}")
            }
        }
    }
}
