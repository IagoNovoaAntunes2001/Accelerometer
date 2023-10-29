package com.example.androidapplication.domain.converter

import com.example.androidapplication.data.dto.AccelerometerResponseDTO
import com.example.androidapplication.domain.entity.AccelerometerEntity

class AccelerometerDataConverter {
    fun convert(accelerometerDTO: AccelerometerResponseDTO): AccelerometerEntity {
        return AccelerometerEntity(
            isSuccessFull = accelerometerDTO.success
        )
    }
}