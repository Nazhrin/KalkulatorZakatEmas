package org.d3if0098.kalkulatorzakatemas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [zakatentity::class],version = 1,exportSchema = false)

abstract class zakatDb : RoomDatabase(){
    abstract val dao:zakatDao

    companion object{
        @Volatile
        private var INSTANCE: zakatDb? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): zakatDb{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        zakatDb::class.java,
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