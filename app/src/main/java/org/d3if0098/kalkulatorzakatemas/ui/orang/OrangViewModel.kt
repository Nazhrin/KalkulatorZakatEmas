package org.d3if0098.kalkulatorzakatemas.ui.orang

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0098.kalkulatorzakatemas.network.ZakatApi
import org.d3if0098.kalkulatorzakatemas.network.ZakatApiService

class OrangViewModel : ViewModel() {
    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                val result = ZakatApi.service.getZakat()
                Log.d("OrangViewModel", "Success: $result")
            } catch (e: Exception) {
                Log.d("OrangViewModel", "Failure: ${e.message}")
            }
        }
    }
}