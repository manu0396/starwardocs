package com.example.starwarsdocs.di

import android.content.Context
import androidx.room.Room
import com.example.starwardocs.BuildConfig
import com.example.starwarsdocs.data.local.LocalDatabase
import com.example.starwarsdocs.data.local.StarWarsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): LocalDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java,
            BuildConfig.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext context: Context):StarWarsDao{
        return provideDB(context).dao()
    }
}