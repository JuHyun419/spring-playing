package com.example.apirequestlogging

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiRequestLoggingApplication

fun main(args: Array<String>) {
    runApplication<ApiRequestLoggingApplication>(*args)
}
