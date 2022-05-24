package org.d3if0098.kalkulatorzakatemas.ui.riwayat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0098.kalkulatorzakatemas.database.ZakatDao
import org.d3if0098.kalkulatorzakatemas.database.ZakatEntity

class RiwayatViewModel (private val db: ZakatDao):ViewModel(){
    private var _deleted = MutableLiveData<Boolean>()
    val deleted: LiveData<Boolean> get() = (_deleted)

    fun hapusData(zakat: ZakatEntity) = viewModelScope.launch {
        _deleted.postValue(false)
        withContext(Dispatchers.IO){
            db.deleteData(zakat)
            _deleted.postValue(true)
        }
    }
}