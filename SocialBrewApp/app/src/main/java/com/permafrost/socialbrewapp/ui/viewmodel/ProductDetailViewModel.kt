package com.permafrost.socialbrewapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.permafrost.socialbrewapp.data.api.ApiClient
import com.permafrost.socialbrewapp.data.api.NewCommentRequest
import com.permafrost.socialbrewapp.data.api.ProductsApiWithObjectUser

import com.permafrost.socialbrewapp.data.api.ProductsApiWithStringUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel : ViewModel() {
    private val _product = MutableStateFlow<ProductsApiWithObjectUser?>(null)
    val product: StateFlow<ProductsApiWithObjectUser?> = _product

    fun loadProduct(productId: String) {
        viewModelScope.launch {
            try {
                val productResponse = ApiClient.apiService.findProductById(productId)
                _product.value = productResponse
            } catch (e: Exception) {
                Log.e("ProductDetailViewModel", "Error fetching product: ${e.message}")
            }
        }
    }
}


