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

class CommentViewModel : ViewModel() {
    private val _product = MutableStateFlow<ProductsApiWithStringUser?>(null)
    val product: StateFlow<ProductsApiWithStringUser?> = _product

    fun addComment(productId: String, newComment: NewCommentRequest, productDetailViewModel: ProductDetailViewModel) {
        viewModelScope.launch {
            try {
                val updatedProduct = ApiClient.apiService.addCommentToProduct(productId, newComment)
                _product.value = updatedProduct
                productDetailViewModel.loadProduct(productId) // Actualizar el producto después de añadir el comentario
            } catch (e: Exception) {
                Log.e("CommentViewModel", "Error adding comment: ${e.message}")
            }
        }
    }
}

