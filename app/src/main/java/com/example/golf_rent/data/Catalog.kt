package com.example.golf_rent.data

import com.example.golf_rent.entities.AvailableFields
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object Catalog {

    suspend fun getCatalog(): List<AvailableFields> = withContext(Dispatchers.IO) {
        val fieldsList = mutableListOf<AvailableFields>()
        val db = DatabaseHandler()
        launch {
            val res =
                db.executeQuery("SELECT avaible_fields.id, field_id, name, date, time FROM avaible_fields join field on field.id = avaible_fields.field_id;")
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

    suspend fun getCatalog(name: String): List<AvailableFields> = withContext(Dispatchers.IO) {
        val fieldsList = mutableListOf<AvailableFields>()
        val db = DatabaseHandler()
        launch {
            val res = db.executeQuery(
                "SELECT avaible_fields.id, field_id, name, date, time " +
                        "FROM avaible_fields" +
                        " join field" +
                        " on field.id = avaible_fields.field_id" +
                        " where name = '$name';"
            )
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

    suspend fun getCatalog(name: String, date: String): List<AvailableFields> =
        withContext(Dispatchers.IO) {
            val fieldsList = mutableListOf<AvailableFields>()
            val db = DatabaseHandler()
            launch {
                val res = db.executeQuery(
                    "SELECT avaible_fields.id, field_id, name, date, time " +
                            "FROM avaible_fields" +
                            " join field" +
                            " on field.id = avaible_fields.field_id" +
                            " where name = '$name' and date = '$date';"
                )
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

    //avaible_fields_id
    suspend fun addRent(fId: Int, userId: Int) {
        val db = DatabaseHandler()
        db.executeQuery("INSERT INTO users_rent (avaible_fields_id, user_id) VALUES ($fId, $userId);")
    }

}
