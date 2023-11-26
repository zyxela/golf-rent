package com.example.golf_rent.repositoryIml

import com.example.golf_rent.data.Catalog
import com.example.golf_rent.data.Catalog.getCatalog
import com.example.golf_rent.entities.AvailableFields
import com.example.golf_rent.repository.CatalogRepository

class CatalogRepositoryImpl : CatalogRepository {
    override suspend fun fetchData(): List<AvailableFields> {
        return getCatalog()
    }

    override suspend fun fetchData(name: String): List<AvailableFields> {
        return getCatalog(name)
    }

    override suspend fun fetchData(name: String, date: String): List<AvailableFields> {
        return getCatalog(name, date)
    }

    override suspend fun addRent(fId: Int, userId: Int) {
        Catalog.addRent(fId, userId)
    }
}