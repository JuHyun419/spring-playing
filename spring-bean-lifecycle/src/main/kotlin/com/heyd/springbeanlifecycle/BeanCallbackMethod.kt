package com.heyd.springbeanlifecycle

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Spring Bean 초기화 3. 메서드 지정
 *  - @Bean ... initMethod, destroyMethod
 *  - 메서드 네이밍 자유롭게 가능
 *  - 외부 라이브러리에도 초기화 및 종료 메서드 적용 가능
 */
class BeanCallbackMethod {

    private fun connect() {
        println("connect: " + "localhost:8080")
    }

    private fun disConnect() {
        println("disConnect: " + "close()")
    }

    fun init() {
        println("init ...")
        connect()
    }

    fun destroy() {
        println("destroy ...")
        disConnect()
    }

}

@Configuration
class MethodConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    fun callback(): BeanCallbackMethod {
        return BeanCallbackMethod()
    }
}