package com.example.golf_rent.data

import com.example.golf_rent.entities.AvailableFields
import com.example.golf_rent.entities.Field
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object Admin {
    suspend fun getFields(): List<Field> = withContext(Dispatchers.IO) {
        val fieldsList = mutableListOf<Field>()
        val db = DatabaseHandler()
        launch {
            val res = db.executeQuery(
                "SELECT * FROM field;"
            )
            res?.use {
                while (it.next()) {
                    fieldsList.add(Field(it.getString("id").toInt(), it.getString("name")))
                }
            }
        }.join()

        return@withContext fieldsList
    }

    suspend fun getAvailableFields(): List<AvailableFields> = withContext(Dispatchers.IO) {
        return@withContext Catalog.getCatalog()
    }

    suspend fun removeField(id: Int) {
        val db = DatabaseHandler()
        db.executeQuery("DELETE FROM avaible_fields WHERE field_id = $id; " +
                "DELETE FROM field WHERE id = $id;")
    }

    suspend fun removeAField(id: Int) {
        val db = DatabaseHandler()
        db.executeQuery("DELETE FROM users_rent WHERE avaible_fields_id = $id; DELETE FROM avaible_fields WHERE id = $id;")
    }

    suspend fun addField(name: String) {
        val db = DatabaseHandler()
        db.executeQuery("INSERT INTO field (name) VALUES ('$name');")
    }

    suspend fun addAField(field: AvailableFields) {
        val db = DatabaseHandler()
        db.executeQuery("INSERT INTO avaible_fields (field_id, date, time) VALUES (${field.fieldId}, '${field.date}', '${field.time}');")
    }
}