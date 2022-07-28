package com.company.parceldeliveryapp.dto

data class CreateUserRequest(
    val mail: String,
    val password: String,
    val firstName: String,
    val lastName: String
)
