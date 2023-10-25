package com.example.androidapplication.domain

import com.example.androidapplication.data.dto.AccelerometerDTO
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("")
    suspend fun getAllMovies(): Response<List<AccelerometerDTO>>
}