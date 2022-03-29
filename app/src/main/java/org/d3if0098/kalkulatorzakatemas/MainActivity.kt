package org.d3if0098.kalkulatorzakatemas

import android.annotation.SuppressLint
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import org.d3if0098.kalkulatorzakatemas.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hitung.setOnClickListener { hitungZakat() }
        binding.reset.setOnClickListener { reset() }

    }

    @SuppressLint("StringFormatInvalid", "StringFormatMatches")
    private fun hitungZakat(){
        val hargaEmas = 892000
        val berat = binding.beratemasInput.text.toString()
        if(TextUtils.isEmpty(berat)){
            Toast.makeText(this,R.string.validasi_berat,Toast.LENGTH_LONG).show()
            return
        }

        val localeID = Locale("in","ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

        val hasil = berat.toDouble() * hargaEmas.toDouble()
        val jumlahZakat = (25.0/100.0) * hasil
        binding.hasil.text = getString(R.string.hasilZakat1, formatRupiah.format(jumlahZakat))

    }

    private fun reset(){
        binding.beratemasInput.setText("")
        binding.hasil.setText("Jumlah rupiah yang harus dikeluarkan adalah : ")
    }
}