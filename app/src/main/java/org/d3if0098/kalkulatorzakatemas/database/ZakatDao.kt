package org.d3if0098.kalkulatorzakatemas.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ZakatDao {
    @Insert
    fun insert (bmi: ZakatEntity)
    @Query("SELECT * FROM zakat")
    fun getZakat(): LiveData<List<ZakatEntity>>
    @Delete
    fun deleteData(zakat: ZakatEntity)
}