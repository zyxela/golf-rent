package com.example.golf_rent.repository

import com.example.golf_rent.entities.AvailableFields

interface CatalogRepository {
    suspend fun fetchData(): List<AvailableFields>
}