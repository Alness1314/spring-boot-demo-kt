package com.example.demokot.users.dto.response

import java.time.LocalDateTime
import java.util.*

data class UserResponse(
    var id: UUID? = null,
    var username: String = "",
    var fullName: String = "",
    var sendExpirationAlert:  Boolean = false,
    var profile: String = "",
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var erased: Boolean = false
)