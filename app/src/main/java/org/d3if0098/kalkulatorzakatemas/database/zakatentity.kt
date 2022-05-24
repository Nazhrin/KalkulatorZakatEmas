package org.d3if0098.kalkulatorzakatemas.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zakat")
data class zakatentity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var uang: String,
    var berat: String
)
