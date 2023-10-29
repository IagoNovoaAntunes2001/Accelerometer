package com.example.androidapplication.di.modules

import com.example.androidapplication.data.remote.AccelerometerApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://enll331ucmlcf.x.pipedream.net/"

val remoteModule = module {
    single<AccelerometerApi> { provideRemoteInstance() }
}

private fun provideRemoteInstance() = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(AccelerometerApi::class.java)
