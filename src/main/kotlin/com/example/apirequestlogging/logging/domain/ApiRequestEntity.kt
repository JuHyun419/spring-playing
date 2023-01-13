package com.example.apirequestlogging.logging.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "API_REQUEST")
class ApiRequestEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 100)
    val methodValue: String,

    @Column(length = 100)
    val apiUri: String,

    @Column
    val payload: String,

    @Column(length = 30)
    val requestIp: String,
) {
    val createdAt: LocalDateTime = LocalDateTime.now()
    val updatedAt: LocalDateTime = LocalDateTime.now()
}