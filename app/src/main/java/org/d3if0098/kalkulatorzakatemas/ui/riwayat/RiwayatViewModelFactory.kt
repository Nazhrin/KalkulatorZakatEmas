package org.d3if0098.kalkulatorzakatemas.ui.riwayat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0098.kalkulatorzakatemas.database.ZakatDao

class RiwayatViewModelFactory (
    private val db: ZakatDao
    ) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RiwayatViewModel::class.java)) {
            return RiwayatViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}