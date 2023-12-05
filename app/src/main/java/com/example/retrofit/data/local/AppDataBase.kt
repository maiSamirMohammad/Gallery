package com.example.retrofit.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofit.data.model.Photo

@Database(entities = [Photo::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun photoDao(): PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null


        fun getInstance(context: Context): AppDataBase{
            return INSTANCE ?:  synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext, AppDataBase::class.java, "my_database"
                )
                    .build()
                INSTANCE=instance
                //return instance
                instance }
        }
    }

}