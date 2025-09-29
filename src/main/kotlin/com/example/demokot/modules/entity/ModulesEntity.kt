package com.example.demokot.modules.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "modules")
open class ModulesEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    var id: UUID,

    @Column(name = "title", nullable = false, unique = true, length = 128)
    var title: String,

    @Column(name = "subtitle", nullable = true, length = 1024)
    var subtitle: String,

    @Column(name = "path", nullable = false, length = 512)
    var path: String,

    @Column(name = "icon", nullable = true, length = 256)
    var icon: String,

    @Column(name = "level", nullable = false, length = 128)
    var level: String,

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "erased", nullable = false)
    var erased: Boolean = false
){
    @PrePersist
    fun prePersist() {
        erased = false
        createdAt = LocalDateTime.now()
        updatedAt = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }
}
