package com.example.golf_rent.data

import android.annotation.SuppressLint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object Registration {
    @SuppressLint("CommitPrefEdits")
    private suspend fun checkUser(login: String): Boolean = withContext(Dispatchers.IO) {
        val db = DatabaseHandler()

        val response = db.executeQuery("SELECT * FROM users WHERE login = '$login';")
        response?.use { it ->
            while (it.next()) {
                if (it.getString("login") == login) {

                    return@withContext true
                }
            }
        }
        return@withContext false

    }


    suspend fun regist(login: String, password: String): Boolean = withContext(Dispatchers.IO) {

        val db = DatabaseHandler()
        var check = false
        launch {
            check = checkUser(login)
        }.join()

        if (!check) {
            db.executeQuery("INSERT INTO users (login, password) VALUES ('$login', '$password');")
        }
        return@withContext (check)


    }
}