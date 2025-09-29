package com.example.demokot.modules.dto

import java.time.LocalDateTime
import java.util.*

data class ModuleResponse(
    var id: UUID? = null,
    var title: String? = "",
    var subtitle: String? = "",
    var path: String? = "",
    var icon: String? = "",
    var level: String? = "",
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var erased: Boolean = false
)
