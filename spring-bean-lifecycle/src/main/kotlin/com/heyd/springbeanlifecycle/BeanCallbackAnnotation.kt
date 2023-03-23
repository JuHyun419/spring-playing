package com.heyd.springbeanlifecycle

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

/**
 * Spring Bean 초기화 1. 어노테이션 기반
 *  - 최신 스프링에서 가장 권장하는 방법
 *  - 단순히 어노테이션만 추가하면 됨
 *  - 패키지: javax... -> 스프링에 종속적인 기술이 아님(자바 표준)
 */
class BeanCallbackAnnotation {

    private fun connect() {
        println("Annotation connect: " + "localhost:8080")
    }

    private fun disConnect() {
        println("Annotation disConnect: " + "close()")
    }

    @PostConstruct
    fun init() {
        println("init ... Annotation")
        connect()
    }

    @PreDestroy
    fun destroy() {
        println("destroy ... Annotation")
        disConnect()
    }

}

@Configuration
class AnnotationConfig {

    @Bean
    fun callback(): BeanCallbackAnnotation {
        return BeanCallbackAnnotation()
    }
}