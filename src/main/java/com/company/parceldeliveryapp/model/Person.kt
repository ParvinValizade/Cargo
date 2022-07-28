package com.company.parceldeliveryapp.model

import javax.persistence.*

@Entity
data class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(unique = true)
    val mail: String?,
    val password: String?,
    val firstName: String?,
    val lastName: String?,
    val role: Role?
) {
    constructor(mail: String,password: String, firstName: String,lastName: String,role:Role) : this(
        null,
        mail = mail,
        password = password,
        firstName = firstName,
        lastName = lastName,
        role = role
    )

    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        null
    )
}
