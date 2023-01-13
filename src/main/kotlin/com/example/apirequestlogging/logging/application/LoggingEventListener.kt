package com.example.apirequestlogging.logging.application

import com.example.apirequestlogging.logging.domain.ApiRequestEntity
import com.example.apirequestlogging.logging.domain.ApiRequestRepository
import com.example.apirequestlogging.logging.domain.LoggingEvent
import lombok.extern.slf4j.Slf4j
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Slf4j
@Component
class LoggingEventListener(
    private val repository: ApiRequestRepository
) {

    @Async
    @EventListener
    fun handleLoggingEvent(loggingEvent: LoggingEvent) {
        repository.save(
            ApiRequestEntity(
                null,
                loggingEvent.method,
                loggingEvent.uri,
                loggingEvent.payload,
                loggingEvent.ip
            )
        )
    }

}