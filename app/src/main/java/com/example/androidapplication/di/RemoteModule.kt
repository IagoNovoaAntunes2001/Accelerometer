package com.example.androidapplication.di

import com.example.androidapplication.data.remote.AccelerometerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://enll331ucmlcf.x.pipedream.net/"

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideAccelerometerService(retrofit: Retrofit): AccelerometerApi {
        return retrofit.create(AccelerometerApi::class.java)
    }
}