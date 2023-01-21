package com.example.apirequestlogging.user.dto

class UserRequest(
    val id: Long,
    val name: String,
    var age: Int
) {
    override fun toString(): String {
        return "UserRequest(id=$id, name='$name', age=$age)"
    }
}