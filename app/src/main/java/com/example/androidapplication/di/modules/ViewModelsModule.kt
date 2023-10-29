package com.example.androidapplication.di.modules

import com.example.androidapplication.domain.converter.AccelerometerDataConverter
import com.example.androidapplication.domain.repository.AccelerometerRepository
import com.example.androidapplication.ui.viewModel.AccelerometerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { provideUserViewModel(get(), get()) }
}

private fun provideUserViewModel(
    accelerometerRepository: AccelerometerRepository,
    converter: AccelerometerDataConverter
) =
    AccelerometerViewModel(accelerometerRepository = accelerometerRepository, converter = converter)
