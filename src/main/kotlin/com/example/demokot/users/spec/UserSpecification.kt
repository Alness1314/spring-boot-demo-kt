package com.example.demokot.users.spec

import com.example.demokot.users.entity.UserEntity
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification
import java.util.*

class UserSpecification : Specification<UserEntity> {
    override fun toPredicate(
        root: Root<UserEntity>,
        query: CriteriaQuery<*>?,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        return null
    }

    fun getSpecificationFilters(params: Map<String, String>): Specification<UserEntity>? {
        var specification: Specification<UserEntity>? = null
        params.forEach { (key, value) ->
            val currentFilter: Specification<UserEntity>? = when (key) {
                "id" -> filterById(value)
                "profile" -> filterByProfile(value)
                "username" -> filterByName(value)
                else -> null
            }
            if (currentFilter != null) {
                specification = specification?.and(currentFilter) ?: currentFilter
            }
        }

        return specification

    }

    private fun filterById(id: String): Specification<UserEntity> =
        Specification { root, _, cb ->
            cb.equal(root.get<UUID>("id"), UUID.fromString(id))
        }

    private fun filterByName(name: String): Specification<UserEntity> =
        Specification { root, _, cb ->
            cb.equal(root.get<String>("username"), name)
        }

    private fun filterByProfile(profile: String): Specification<UserEntity> =
        Specification { root, _, cb ->
            cb.equal(root.get<String>("profile"), profile)
        }

}