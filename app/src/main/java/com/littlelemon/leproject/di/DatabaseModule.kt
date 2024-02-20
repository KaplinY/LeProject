package com.littlelemon.leproject.di

import android.content.Context
import com.littlelemon.leproject.data.CityDao
import com.littlelemon.leproject.data.CityRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideCityDao(appDatabase: CityRoomDatabase): CityDao {
        return appDatabase.cityDao()
    }

    @Provides
    @Singleton
    fun provideAddDatabase(@ApplicationContext context: Context): CityRoomDatabase {
        return CityRoomDatabase.getInstance(context)
    }
}