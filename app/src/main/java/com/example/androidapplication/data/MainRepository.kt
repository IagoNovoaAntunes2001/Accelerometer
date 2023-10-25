package com.example.androidapplication.data

import com.example.androidapplication.domain.entity.AccelerometerDTO

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun postAccelerometer(x: String, y: String, xy: String) = retrofitService.getAllAccelerometer(
        AccelerometerDTO(x, y, xy)
    )

}