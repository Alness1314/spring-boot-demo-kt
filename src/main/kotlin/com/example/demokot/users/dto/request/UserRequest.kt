package com.example.demokot.users.dto.request

data class UserRequest(
    var username: String = "",
    var password: String = "",
    var fullName: String = "",
    var sendExpirationAlert:  Boolean = false,
    var profile: String = ""
)