package com.example.demokot.common

import org.springframework.http.HttpStatus

data class ResponseServerDto(
    var message: String = "",
    var data: MutableMap<String, Any> = mutableMapOf(),
    var status: Boolean = false,
    var code: HttpStatus = HttpStatus.OK
    )