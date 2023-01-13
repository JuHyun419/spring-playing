package com.example.apirequestlogging.logging.domain

import org.springframework.data.jpa.repository.JpaRepository

interface ApiRequestRepository: JpaRepository<ApiRequestEntity, Long>