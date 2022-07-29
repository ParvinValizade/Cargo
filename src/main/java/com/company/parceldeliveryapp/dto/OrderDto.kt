package com.company.parceldeliveryapp.dto

import com.company.parceldeliveryapp.model.Status

data class OrderDto(
    val id:Long,
    val name:String,
    val destination: String,
    val status: Status,
    val userMail:String
)
