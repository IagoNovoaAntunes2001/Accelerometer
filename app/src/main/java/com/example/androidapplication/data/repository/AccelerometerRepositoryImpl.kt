package com.example.androidapplication.data.repository

import com.example.androidapplication.data.dto.AccelerometerDTO
import com.example.androidapplication.data.remote.AccelerometerApi
import com.example.androidapplication.domain.repository.AccelerometerRepository

class AccelerometerRepositoryImpl(
    private val accelerometerApi: AccelerometerApi
) : AccelerometerRepository {
    override suspend fun postAccelerometer(x: String, y: String, xy: String) =
        accelerometerApi.getAllAccelerometer(
            AccelerometerDTO(x, y, xy)
        )
}
