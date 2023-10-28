package com.example.androidapplication.data.repository

import com.example.androidapplication.data.dto.AccelerometerDTO
import com.example.androidapplication.data.remote.AccelerometerApi
import com.example.androidapplication.domain.AccelerometerRepository
import javax.inject.Inject
import javax.inject.Singleton

class AccelerometerRepositoryImpl @Inject constructor(
    private val accelerometerApi: AccelerometerApi
) : AccelerometerRepository {

    override suspend fun postAccelerometer(x: String, y: String, xy: String) =
        accelerometerApi.getAllAccelerometer(
            AccelerometerDTO(x, y, xy)
        )
}
