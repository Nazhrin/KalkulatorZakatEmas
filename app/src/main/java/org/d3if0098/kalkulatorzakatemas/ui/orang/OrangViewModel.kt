package org.d3if0098.kalkulatorzakatemas.ui.orang

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0098.kalkulatorzakatemas.data.model.Orang
import org.d3if0098.kalkulatorzakatemas.network.ApiStatus
import org.d3if0098.kalkulatorzakatemas.network.UpdateWorker
import org.d3if0098.kalkulatorzakatemas.network.ZakatApi
import org.d3if0098.kalkulatorzakatemas.network.ZakatApiService

class OrangViewModel : ViewModel() {
    private val data = MutableLiveData<List<Orang>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(ZakatApi.service.getZakat())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("OrangViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }
    fun getData(): LiveData<List<Orang>> = data
    fun getStatus(): LiveData<ApiStatus> = status

}