package com.heyd.springbeanlifecycle

import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Configuration

/**
 * Spring Bean 초기화 2. 인터페이스 구현
 *  - 스프링에 의존
 *  - 메서드 이름 변경 불가능
 *  - 거의 사용 X
 */
@Configuration
class BeanCallbackInterface: InitializingBean, DisposableBean {

    /* 의존관계 설정 후 호출 */
    override fun afterPropertiesSet() {
        println("afterPropertiesSet() : start")
    }

    /* Bean 소멸 전 호출 */
    override fun destroy() {
        println("destroy() : end")
    }
}