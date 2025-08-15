package com.example.demokot.users.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    var id: UUID? = null,

    @Column(name = "username", nullable = false, unique = true, length = 64)
    var username: String = "",

    @Column(name = "password", nullable = false, length = 256)
    var password: String = "",

    @Column(name = "full_name", nullable = false, length = 256)
    var fullName: String = "",

    @Column(name = "send_expiration_alert", nullable = false)
    var sendExpirationAlert: Boolean = false,

    @Column(name = "profile", nullable = false, length = 256)
    var profile: String = "",

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "erased", nullable = false)
    var erased: Boolean = false
) {

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
