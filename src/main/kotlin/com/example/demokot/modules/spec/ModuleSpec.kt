package com.example.demokot.modules.spec

import com.example.demokot.modules.entity.ModulesEntity
import jakarta.persistence.criteria.*
import org.springframework.data.jpa.domain.Specification
import java.util.UUID

class ModuleSpec: Specification<ModulesEntity> {
    override fun toPredicate(
        root: Root<ModulesEntity>,
        query: CriteriaQuery<*>?,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        return null
    }

    fun findSpecFilters(params: Map<String, String>): Specification<ModulesEntity>? {
        var specification: Specification<ModulesEntity>? = null
        params.forEach { (key, value) ->
            val currentFilter: Specification<ModulesEntity>? = when(key) {
                "id" -> filterById(value)
                "title" -> filterByTitle(value)
                "level" -> filterByLevel(value)
                "erased" -> filterByErased(value)
                else -> null
            }

            if(currentFilter!=null){
                specification = specification?.and(currentFilter) ?: currentFilter
            }
        }
        return specification
    }

    private fun filterById(value: String): Specification<ModulesEntity> = Specification<ModulesEntity> {
        root, _, cb -> cb.equal(root.get<UUID>("id"), UUID.fromString(value))
    }
    private fun filterByLevel(value: String): Specification<ModulesEntity> = Specification<ModulesEntity> {
            root, _, cb -> cb.equal(root.get<String>("level"), value)
    }

    private fun filterByTitle(value: String): Specification<ModulesEntity> = Specification<ModulesEntity> {
            root, _, cb -> cb.equal(root.get<String>("title"), value)
    }

    private fun filterByErased(value: String): Specification<ModulesEntity> = Specification<ModulesEntity> {
            root, _, cb -> cb.equal(root.get<Boolean>("erased"), value.toBoolean())
    }

}