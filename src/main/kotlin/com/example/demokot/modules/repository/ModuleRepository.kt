package com.example.demokot.modules.repository

import com.example.demokot.modules.entity.ModulesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.UUID

interface ModuleRepository: JpaRepository<ModulesEntity, UUID>, JpaSpecificationExecutor<ModulesEntity> {
}