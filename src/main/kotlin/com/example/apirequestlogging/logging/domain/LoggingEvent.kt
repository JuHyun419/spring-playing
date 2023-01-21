package com.example.apirequestlogging.logging.domain

class LoggingEvent(
    val method: String,
    val uri: String,
    val payload: String,
    val ip: String
)