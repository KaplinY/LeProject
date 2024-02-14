package com.littlelemon.leproject.di

import com.littlelemon.leproject.data.CityDao
import com.littlelemon.leproject.data.CityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCityRepository(cityDao: CityDao) : CityRepository {
        return CityRepository(cityDao)
    }
}