package com.example.androidapplication.di

import com.example.androidapplication.di.modules.convertersModule
import com.example.androidapplication.di.modules.remoteModule
import com.example.androidapplication.di.modules.repositoriesModule
import com.example.androidapplication.di.modules.viewModelsModule

val appModules = listOf(
    remoteModule,
    repositoriesModule,
    viewModelsModule,
    convertersModule
)
