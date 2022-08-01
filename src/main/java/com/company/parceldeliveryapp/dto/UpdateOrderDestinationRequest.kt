package com.company.parceldeliveryapp.dto

data class UpdateOrderDestinationRequest(
    val orderId: Long,
    val destination: String
)
