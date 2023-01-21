package com.example.apirequestlogging.user.ui

import com.example.apirequestlogging.user.dto.UserRequest
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserController {

    private val log = LoggerFactory.getLogger(UserController::class.java)

    @GetMapping("/users/{userId}")
    fun get(@PathVariable userId: String): ResponseEntity<String> {
        log.info("Request ::: [userId: $userId]")

        return ResponseEntity.ok("Success")
    }

    @PostMapping("/users")
    fun post(@RequestBody request: UserRequest): ResponseEntity<UserRequest> {
        log.info("Request ::: [${request.id}, ${request.name}, ${request.age}]")

        return ResponseEntity.ok(request)
    }

    @GetMapping("throw")
    fun throwsGet(@RequestBody request: UserRequest) {
        log.info("Request ::: [${request.id}, ${request.name}, ${request.age}]")

        request.age = request.age / 0
    }

}