package com.example.golf_rent.repositoryIml

import com.example.golf_rent.data.History.getHistory
import com.example.golf_rent.entities.AvailableFields
import com.example.golf_rent.repository.HistoryRepository

class HistoryRepositoryImpl : HistoryRepository {
    override suspend fun fetchHistoryData(userId:Int): List<AvailableFields> {
        return getHistory(userId)
    }
}