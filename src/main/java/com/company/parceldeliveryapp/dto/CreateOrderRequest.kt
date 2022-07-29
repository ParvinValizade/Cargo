package com.company.parceldeliveryapp.dto

data class CreateOrderRequest(
    val name:String,
    val destination: String,
    val userMail:String
)
