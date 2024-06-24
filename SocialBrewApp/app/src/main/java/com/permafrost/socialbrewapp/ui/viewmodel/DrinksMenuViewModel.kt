package com.permafrost.socialbrewapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.permafrost.socialbrewapp.data.api.ApiClient

import com.permafrost.socialbrewapp.data.api.ProductsApiWithStringUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DrinksMenuViewModel : ViewModel() {
     private val _products = MutableStateFlow<List<ProductsApiWithStringUser>>(emptyList())
    val products: StateFlow<List<ProductsApiWithStringUser>> = _products


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

}
