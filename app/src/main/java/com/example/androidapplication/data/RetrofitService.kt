package com.example.androidapplication.data

import com.example.androidapplication.data.dto.AccelerometerDTO
import com.example.androidapplication.data.dto.AccelerometerResponseDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {

    @POST("accelerometer")
    suspend fun getAllAccelerometer(@Body bodyRequest: AccelerometerDTO): Response<AccelerometerResponseDTO>

    companion object {
        private var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            return retrofitService ?: Retrofit.Builder()
                .baseUrl("https://enll331ucmlcf.x.pipedream.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService::class.java)
        }
    }
}