package com.company.parceldeliveryapp.model

import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?,
    val name:String?,
    var destination: String?,
    var status: Status?,

    val userMail: String?,
    var courierMail: String?
){
    constructor(name: String?, destination: String?, status: Status?, userMail: String?, courierMail: String?):this(
        null,
        name=name,
        destination = destination,
        status = status,
        userMail = userMail,
        courierMail = courierMail
    )
    constructor(name: String,destination: String,status: Status,userMail: String):this(
        name =name,
        destination = destination,
        status = status,
        userMail = userMail,
        null
    )
    constructor():this(
        null,
        null,
        null,
        null,
        null
    )
}
