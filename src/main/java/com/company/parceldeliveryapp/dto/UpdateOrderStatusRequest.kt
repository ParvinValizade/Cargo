package com.company.parceldeliveryapp.dto

data class UpdateOrderStatusRequest(
    val orderId: Long,
    val status: String
)
