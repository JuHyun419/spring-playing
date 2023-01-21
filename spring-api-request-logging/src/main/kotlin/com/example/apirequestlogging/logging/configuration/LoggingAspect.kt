package com.example.apirequestlogging.logging.configuration

import com.example.apirequestlogging.logging.domain.LoggingEvent
import lombok.extern.slf4j.Slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest

@Slf4j
@Aspect
@Component
class LoggingAspect(
    val eventPublisher: ApplicationEventPublisher
) {

    companion object {
        const val CONTROLLER_PUBLIC_METHOD =
            "execution(public * com.example.apirequestlogging.user.ui.*Controller.*(..))"
    }

    val log: Logger = LoggerFactory.getLogger(javaClass)

    @Before(CONTROLLER_PUBLIC_METHOD)
    private fun loggingBefore(joinPoint: JoinPoint) {
        val request: HttpServletRequest =
            (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request

        log.info(
            "[REQUEST][${request.remoteAddr} ${request.method} ${request.requestURI}, Payload: ${getPayload(joinPoint)}]"
        )

        // Api Request save event
        eventPublisher.publishEvent(
            LoggingEvent(
                request.method,
                request.requestURI,
                getPayload(joinPoint),
                request.remoteAddr
            )
        )
    }

    @AfterReturning(value = CONTROLLER_PUBLIC_METHOD, returning = "result")
    private fun loggingAfter(result: ResponseEntity<*>) {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request

        log.info("[RESPONSE][${request.remoteAddr} ${request.method} ${request.requestURI}, Result: ${result.body}]")
    }

    @AfterThrowing(value = CONTROLLER_PUBLIC_METHOD, throwing = "e")
    private fun loggingAfterThrowing(e: Exception) {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request

        log.info("[RESPONSE][${request.remoteAddr} ${request.method} ${request.requestURI}, Exception: $e]")
    }

    private fun getPayload(joinPoint: JoinPoint): String {
        return Arrays.stream(joinPoint.args)
            .map { obj: Any -> obj.toString() }
            .collect(Collectors.joining(", "))
    }

}