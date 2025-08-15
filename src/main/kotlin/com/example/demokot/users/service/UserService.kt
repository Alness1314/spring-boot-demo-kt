package com.example.demokot.users.service

import com.example.demokot.common.ResponseServerDto
import com.example.demokot.users.dto.request.UserRequest
import com.example.demokot.users.dto.response.UserResponse

interface UserService {
    fun find(params: Map<String, String>): List<UserResponse>
    fun findOne(id: String): UserResponse
    fun save(request: UserRequest): UserResponse
    fun update(id: String, request: UserRequest): UserResponse
    fun delete(id: String): ResponseServerDto
}