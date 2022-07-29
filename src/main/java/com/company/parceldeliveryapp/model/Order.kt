package com.company.parceldeliveryapp.model

import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?,
    val name:String?,
    val destination: String?,
    val status: Status?,

    val userMail: String?
){
    constructor(name: String,destination: String,status: Status,userMail: String):this(
        null,
        name=name,
        destination = destination,
        status = status,
        userMail = userMail
    )
    constructor():this(
        null,
        null,
        null,
        null,
        null
    )
}
