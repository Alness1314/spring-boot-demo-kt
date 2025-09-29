package com.example.demokot.modules.service

import com.example.demokot.common.ResponseServerDto
import com.example.demokot.modules.dto.ModuleRequest
import com.example.demokot.modules.dto.ModuleResponse

interface ModuleService {
    fun findOne(id: String): ModuleResponse
    fun find(params: Map<String, String>): List<ModuleResponse>
    fun save(request: ModuleRequest): ModuleResponse
    fun update(id: String, request: ModuleRequest): ModuleResponse
    fun delete(id:String): ResponseServerDto
}