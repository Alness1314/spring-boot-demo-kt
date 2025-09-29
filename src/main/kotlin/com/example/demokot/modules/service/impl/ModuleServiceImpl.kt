package com.example.demokot.modules.service.impl

import com.example.demokot.common.ResponseServerDto
import com.example.demokot.modules.dto.ModuleRequest
import com.example.demokot.modules.dto.ModuleResponse
import com.example.demokot.modules.entity.ModulesEntity
import com.example.demokot.modules.repository.ModuleRepository
import com.example.demokot.modules.service.ModuleService
import com.example.demokot.modules.spec.ModuleSpec
import com.example.demokot.users.entity.UserEntity
import jakarta.annotation.PostConstruct
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class ModuleServiceImpl(
    private val moduleRepo: ModuleRepository
): ModuleService {
    private val mapper = ModelMapper()
    val notFound = "Resource not found."

    @PostConstruct
    fun init(){
        mapper.configuration.apply {
            isSkipNullEnabled = true
            isFieldMatchingEnabled = true
            matchingStrategy = MatchingStrategies.STRICT
        }
    }
    override fun findOne(id: String): ModuleResponse {
        val module: ModulesEntity = moduleRepo.findById(UUID.fromString(id))
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, notFound) }
        return mapper(module)
    }

    override fun find(params: Map<String, String>): List<ModuleResponse> {
        val specification = filters(params)
        return moduleRepo.findAll(specification)
            .map { mapper(it) }
    }

    override fun save(request: ModuleRequest): ModuleResponse {
        val newModule: ModulesEntity = mapper.map(request, ModulesEntity::class.java)
        try {
            return mapper(moduleRepo.save(newModule))
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error to save entity.", ex)
        }
    }

    override fun update(id: String, request: ModuleRequest): ModuleResponse {
        TODO("Not yet implemented")
    }

    override fun delete(id: String): ResponseServerDto {
        TODO("Not yet implemented")
    }

    private fun mapper(source: ModulesEntity): ModuleResponse{
        return mapper.map(source, ModuleResponse::class.java)
    }

    private fun filters(params: Map<String, String>): Specification<ModulesEntity>? {
        return ModuleSpec().findSpecFilters(params)
    }
}