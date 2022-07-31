package com.company.parceldeliveryapp.dto

import com.company.parceldeliveryapp.model.CourierStatus

data class CourierDto(
    val mail: String,
    val firstName: String,
    val lastName: String,
    val role: RoleDto,
    val status:CourierStatus
)
