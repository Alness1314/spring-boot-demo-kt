package com.example.demokot.users.controller

import com.example.demokot.common.ResponseServerDto
import com.example.demokot.users.dto.request.UserRequest
import com.example.demokot.users.dto.response.UserResponse
import com.example.demokot.users.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${api.prefix}/users")
@Tag(name = "users", description = ".")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun findAll(@RequestParam params: Map<String, String>): ResponseEntity<List<UserResponse>> {
        val response = userService.find(params)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: String): ResponseEntity<UserResponse> {
        val response = userService.findOne(id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    fun create(@Valid @RequestBody request: UserRequest): ResponseEntity<UserResponse> {
        val response = userService.save(request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @Valid @RequestBody request: UserRequest): ResponseEntity<UserResponse> {
        val response = userService.update(id, request)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<ResponseServerDto> {
        val response = userService.delete(id)
        return ResponseEntity(response, HttpStatus.OK)
    }
}