package com.example.golf_rent.data

import com.example.golf_rent.entities.AvailableFields
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object History {
    suspend fun getHistory(userId:Int):List<AvailableFields> = withContext(Dispatchers.IO){
        val fieldsList = mutableListOf<AvailableFields>()
        val db = DatabaseHandler()

        launch {
            val res = db.executeQuery(
                "SELECT * FROM users_rent " +
                    "JOIN avaible_fields " +
                        "ON avaible_fields.id = users_rent.avaible_fields_id " +
                        "JOIN field ON avaible_fields.field_id = field.id " +
                    "WHERE users_rent.user_id = $userId;")//
            res?.use {
                while (it.next()) {
                    val match = AvailableFields(
                        it.getString("id").toInt(),
                        it.getString("field_id").toInt(),
                        it.getString("name"),
                        it.getString("date"),
                        it.getString("time"),
                    )
                    fieldsList.add(match)
                }
            }
        }.join()

        return@withContext fieldsList

    }
}