package org.d3if0098.kalkulatorzakatemas.ui.hitung

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0098.kalkulatorzakatemas.R
import org.d3if0098.kalkulatorzakatemas.database.zakatDao
import org.d3if0098.kalkulatorzakatemas.database.zakatDb
import org.d3if0098.kalkulatorzakatemas.database.zakatentity
import org.d3if0098.kalkulatorzakatemas.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class HitungViewModel(private val db: zakatDao) : ViewModel() {

    private var hasilZakat = MutableLiveData<String>()
    val getHasilZakat: LiveData<String> get() = (hasilZakat)

    val data = db.getLastZakat()
    fun riwayat(uang:String,berat:String){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                val dataZakat = zakatentity(
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
                val dataZakat = zakatentity(
                    uang = formatRupiah.format(jumlahZakat).toString(),
                    berat = berat
                )
                db.insert(dataZakat)
            }
        }
        hasilZakat.value = formatRupiah.format(jumlahZakat).toString()
    }
}