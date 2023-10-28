package com.example.androidapplication.domain

import com.example.androidapplication.data.dto.AccelerometerResponseDTO
import retrofit2.Response

interface AccelerometerRepository {
    suspend fun postAccelerometer(
        x: String,
        y: String,
        xy: String
    ): Response<AccelerometerResponseDTO>
}