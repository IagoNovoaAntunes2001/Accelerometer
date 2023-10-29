package com.example.androidapplication.di.modules

import com.example.androidapplication.domain.converter.AccelerometerDataConverter
import org.koin.dsl.module

val convertersModule = module {
    single { provideUserDataConverter() }
}

private fun provideUserDataConverter() = AccelerometerDataConverter()
