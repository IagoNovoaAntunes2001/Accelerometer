package com.example.androidapplication.data

import com.example.androidapplication.domain.entity.AccelerometerDTO
import com.example.androidapplication.domain.entity.AccelerometerResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {

    @POST("accelerometer")
    suspend fun getAllAccelerometer(@Body bodyRequest: AccelerometerDTO): Response<AccelerometerResponse>

    companion object {
        private var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://enll331ucmlcf.x.pipedream.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}