package com.permafrost.socialbrewapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.permafrost.socialbrewapp.data.api.ApiClient
import com.permafrost.socialbrewapp.data.api.UserApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SelectionViewModel : ViewModel() {
    private val _barAccounts = MutableStateFlow<List<UserApi>>(emptyList())
    val barAccounts: StateFlow<List<UserApi>> get() = _barAccounts

    init {
        fetchBarAccounts()
    }

    private fun fetchBarAccounts() {
        viewModelScope.launch {
            try {
                val bars = ApiClient.apiService.findAllBars().filter { it.roles.contains("Bar") }
                _barAccounts.value = bars
            } catch (e: Exception) {
                // Handle the error appropriately
            }
        }
    }
}