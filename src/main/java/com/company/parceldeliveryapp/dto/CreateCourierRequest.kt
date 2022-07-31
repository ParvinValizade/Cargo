package com.company.parceldeliveryapp.dto

import com.company.parceldeliveryapp.model.CourierStatus

data class CreateCourierRequest(
    val mail: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val status: CourierStatus
)
