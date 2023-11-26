package com.example.golf_rent.repositoryIml

import com.example.golf_rent.data.Admin
import com.example.golf_rent.entities.AvailableFields
import com.example.golf_rent.entities.Field
import com.example.golf_rent.repository.AdminRepository

class AdminRepositoryImpl: AdminRepository {
    override suspend fun fetchFields(): List<Field> {
        return Admin.getFields()
    }

    override suspend fun fetchAvailableFields(): List<AvailableFields> {
        return Admin.getAvailableFields()
    }

    override suspend fun removeField(id: Int) {
        Admin.removeField(id)
    }

    override suspend fun removeAField(id: Int) {
        Admin.removeAField(id)
    }

    override suspend fun addField(name: String) {
        Admin.addField(name)
    }

    override suspend fun addAField(field: AvailableFields) {
        Admin.addAField(field)
    }
}