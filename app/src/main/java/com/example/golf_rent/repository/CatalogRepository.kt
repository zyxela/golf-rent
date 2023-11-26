package com.example.golf_rent.repository

import com.example.golf_rent.entities.AvailableFields

interface CatalogRepository {
    suspend fun fetchData(): List<AvailableFields>
    suspend fun fetchData(name:String): List<AvailableFields>
    suspend fun fetchData(name:String, date:String): List<AvailableFields>
    suspend fun addRent(fId:Int, userId:Int)
}