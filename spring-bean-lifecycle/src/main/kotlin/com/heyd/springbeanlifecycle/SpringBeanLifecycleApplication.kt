package com.heyd.springbeanlifecycle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBeanLifecycleApplication

fun main(args: Array<String>) {
    runApplication<SpringBeanLifecycleApplication>(*args)
}
