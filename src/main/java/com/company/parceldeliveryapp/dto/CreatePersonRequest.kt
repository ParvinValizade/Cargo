package com.company.parceldeliveryapp.dto

data class CreatePersonRequest(
    val mail: String,
    val password: String,
    val firstName: String,
    val lastName: String
)
