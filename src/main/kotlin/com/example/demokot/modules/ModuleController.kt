package com.example.demokot.modules

import com.example.demokot.modules.dto.ModuleRequest
import com.example.demokot.modules.dto.ModuleResponse
import com.example.demokot.modules.service.ModuleService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${api.prefix}/modules")
@Tag(name = "modules", description = ".")
class ModuleController(
    private val modulesService: ModuleService
) {
    @GetMapping
    fun findAll(@RequestParam params: Map<String, String>): ResponseEntity<List<ModuleResponse>> {
        val response = modulesService.find(params)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: String): ResponseEntity<ModuleResponse> {
        val response = modulesService.findOne(id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    fun create(@Valid @RequestBody request: ModuleRequest): ResponseEntity<ModuleResponse> {
        val response = modulesService.save(request)
        return ResponseEntity(response, HttpStatus.OK)
    }
}