package com.example.demokot.users.repository

import com.example.demokot.users.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.UUID

interface UserRepository: JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {
}