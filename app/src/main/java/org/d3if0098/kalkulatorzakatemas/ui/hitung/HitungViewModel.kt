package org.d3if0098.kalkulatorzakatemas.ui.hitung

import android.app.Application
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0098.kalkulatorzakatemas.R
import org.d3if0098.kalkulatorzakatemas.database.ZakatDao
import org.d3if0098.kalkulatorzakatemas.database.ZakatEntity
import org.d3if0098.kalkulatorzakatemas.network.UpdateWorker
import java.text.NumberFormat
import java.util.*

class HitungViewModel(private val db: ZakatDao) : ViewModel() {

    private var hasilZakat = MutableLiveData<String>()
    val getHasilZakat: LiveData<String> get() = (hasilZakat)

    val data = db.getZakat()

    fun riwayat(uang:String,berat:String){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                val dataZakat = ZakatEntity(
                    uang = uang,
                    berat = berat
                )
                db.insert(dataZakat)
            }
        }
    }

    fun hitungZakat(berat: String){
        hasilZakat.value = ""
        val hargaEmas = 892000
        val berat = berat

        val localeID = Locale("in","ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

        val hasil = berat.toDouble() * hargaEmas.toDouble()
        val jumlahZakat = (25.0/100.0) * hasil

        viewModelScope.launch{
            withContext(Dispatchers.IO){
                val dataZakat = ZakatEntity(
                    uang = formatRupiah.format(jumlahZakat).toString(),
                    berat = berat
                )
                db.insert(dataZakat)
            }
        }
        hasilZakat.value = formatRupiah.format(jumlahZakat).toString()
    }
    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            "updater",
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

}