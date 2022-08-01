package com.company.parceldeliveryapp.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Courier(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(unique = true)
    val mail: String?,
    val password: String?,
    val firstName: String?,
    val lastName: String?,
    val role: Role?,
    var status: CourierStatus?
) {
    constructor(mail: String,password: String, firstName: String,lastName: String,role:Role,status: CourierStatus) : this(
        null,
        mail = mail,
        password = password,
        firstName = firstName,
        lastName = lastName,
        role = role,
        status = status
    )

    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}