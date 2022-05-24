package org.d3if0098.kalkulatorzakatemas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [ZakatEntity::class],version = 1,exportSchema = false)

abstract class ZakatDb : RoomDatabase(){
    abstract val dao: ZakatDao

    companion object{
        @Volatile
        private var INSTANCE: ZakatDb? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): ZakatDb{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ZakatDb::class.java,
                        "zakat.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}