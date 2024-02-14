package com.littlelemon.leproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(City::class)], version = 1, exportSchema = false)
abstract class CityRoomDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var INSTANCE: CityRoomDatabase? = null

        fun getInstance(context: Context): CityRoomDatabase {
           synchronized(this) {
               var instance = INSTANCE

               if (instance == null) {
                   instance = Room.databaseBuilder(
                       context.applicationContext,
                       CityRoomDatabase::class.java,
                       "city_database"
                   ).fallbackToDestructiveMigration()
                       .build()

                   INSTANCE = instance
               }
               return instance
           }
        }
    }
}