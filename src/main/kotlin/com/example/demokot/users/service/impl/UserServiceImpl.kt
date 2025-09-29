package com.example.demokot.users.service.impl

import com.example.demokot.common.ResponseServerDto
import com.example.demokot.users.dto.request.UserRequest
import com.example.demokot.users.dto.response.UserResponse
import com.example.demokot.users.entity.UserEntity
import com.example.demokot.users.repository.UserRepository
import com.example.demokot.users.service.UserService
import com.example.demokot.users.spec.UserSpecification
import jakarta.annotation.PostConstruct
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    private val modelMapper = ModelMapper();
    val notFound = "Resource not found.";

    @PostConstruct
    fun init(){
        modelMapper.configuration.apply {
            isSkipNullEnabled = true
            isFieldMatchingEnabled = true
            matchingStrategy = MatchingStrategies.STRICT
        }
    }

    override fun find(params: Map<String, String>): List<UserResponse> {
        val specification = filterWithParameters(params)
        return userRepository.findAll(specification)
            .map { mapperDto(it) }
    }

    override fun findOne(id: String): UserResponse {
        val user: UserEntity = userRepository.findById(UUID.fromString(id))
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, notFound) }
        return mapperDto(user)
    }

    override fun save(request: UserRequest): UserResponse {
        val newUser: UserEntity = modelMapper.map(request, UserEntity::class.java)
        try {
            return mapperDto(userRepository.save(newUser))
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error to save entity.", ex)
        }
    }

    override fun update(id: String, request: UserRequest): UserResponse {
        val existingUser: UserEntity = userRepository.findById(UUID.fromString(id))
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, notFound) }
        modelMapper.map(request, existingUser)
        try {
            return mapperDto(userRepository.save(existingUser))
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error to update entity.", ex)
        }
    }

    override fun delete(id: String): ResponseServerDto {
        val user: UserEntity = userRepository.findById(UUID.fromString(id))
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, notFound) }
        try {
            userRepository.delete(user)
            return ResponseServerDto("Resourde deleted", mutableMapOf(), true, HttpStatus.OK)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error to delete entity.", ex)
        }
    }

    private fun mapperDto(source: UserEntity): UserResponse {
        return modelMapper.map(source, UserResponse::class.java)
    }

    private fun filterWithParameters(parameters: Map<String, String>): Specification<UserEntity>? {
        return UserSpecification().getSpecificationFilters(parameters)
    }

}