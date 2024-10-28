package com.example.starwarsdocs.di

import com.example.starwardocs.BuildConfig.BASE_URL
import com.example.starwarsdocs.data.remote.StarWarsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): StarWarsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().apply {
                //TODO:Apply request changes
            }.build())
            .baseUrl(BASE_URL)
            .build()
            .create(StarWarsApi::class.java)
    }
}