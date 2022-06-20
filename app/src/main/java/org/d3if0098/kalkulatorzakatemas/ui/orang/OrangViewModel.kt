package org.d3if0098.kalkulatorzakatemas.ui.orang

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0098.kalkulatorzakatemas.data.model.Orang
import org.d3if0098.kalkulatorzakatemas.network.ZakatApi
import org.d3if0098.kalkulatorzakatemas.network.ZakatApiService

class OrangViewModel : ViewModel() {
    private val data = MutableLiveData<List<Orang>>()
    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                data.postValue(ZakatApi.service.getZakat())
            } catch (e: Exception) {
                Log.d("OrangViewModel", "Failure: ${e.message}")
            }
        }
    }
    fun getData(): LiveData<List<Orang>> = data

}