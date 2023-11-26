package com.example.golf_rent.repository

import com.example.golf_rent.entities.AvailableFields
import com.example.golf_rent.entities.Field

interface AdminRepository {
    suspend fun fetchFields(): List<Field>
    suspend fun fetchAvailableFields(): List<AvailableFields>

    suspend fun removeField(id: Int)
    suspend fun removeAField(id: Int)

    suspend fun addField(name: String)
    suspend fun addAField(field: AvailableFields)
}