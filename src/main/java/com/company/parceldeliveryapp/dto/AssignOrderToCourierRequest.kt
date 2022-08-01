package com.company.parceldeliveryapp.dto

data class AssignOrderToCourierRequest(
    val orderId: Long,
    val courierMail: String
)
