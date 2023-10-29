package com.example.androidapplication.di.modules

import com.example.androidapplication.data.remote.AccelerometerApi
import com.example.androidapplication.data.repository.AccelerometerRepositoryImpl
import com.example.androidapplication.domain.repository.AccelerometerRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<AccelerometerRepository> { provideUserRepository(get()) }
}

private fun provideUserRepository(accelerometerApi: AccelerometerApi) =
    AccelerometerRepositoryImpl(accelerometerApi = accelerometerApi)