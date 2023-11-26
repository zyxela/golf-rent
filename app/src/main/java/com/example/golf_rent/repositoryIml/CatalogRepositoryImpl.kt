package com.example.golf_rent.repositoryIml

import com.example.golf_rent.data.Catalog.getCatalog
import com.example.golf_rent.entities.AvailableFields
import com.example.golf_rent.repository.CatalogRepository

class CatalogRepositoryImpl: CatalogRepository {
    override suspend fun fetchData(): List<AvailableFields> {
        return getCatalog()
    }
}