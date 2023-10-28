package com.example.androidapplication.data.remote

import com.example.androidapplication.data.dto.AccelerometerDTO
import com.example.androidapplication.data.dto.AccelerometerResponseDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AccelerometerApi {

    @POST("accelerometer")
    suspend fun getAllAccelerometer(@Body bodyRequest: AccelerometerDTO): Response<AccelerometerResponseDTO>
}