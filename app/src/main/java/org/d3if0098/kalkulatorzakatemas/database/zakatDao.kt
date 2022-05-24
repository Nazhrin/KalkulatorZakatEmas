package org.d3if0098.kalkulatorzakatemas.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface zakatDao {
    @Insert
    fun insert (bmi: zakatentity)
    @Query("SELECT * FROM zakat")
    fun getLastZakat(): LiveData<zakatentity?>
}