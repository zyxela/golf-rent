package com.example.golf_rent.repository

import com.example.golf_rent.entities.AvailableFields

interface HistoryRepository {
    suspend fun fetchHistoryData(userId:Int):List<AvailableFields>
}