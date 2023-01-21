package com.example.apirequestlogging

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class ApiRequestLoggingApplication

fun main(args: Array<String>) {
    runApplication<ApiRequestLoggingApplication>(*args)
}
