package com.example.golf_rent.data

import android.annotation.SuppressLint
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Authorization {
    @SuppressLint("CommitPrefEdits")
    suspend fun checkUser(login: String, password: String, context: Context): Boolean =
        withContext(Dispatchers.IO) {
            val db = DatabaseHandler()

            val sharedPreferences =
                context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

            val response =
                db.executeQuery("SELECT * FROM users WHERE login = '$login' AND password = '$password';")
            response?.use {
                while (it.next()) {
                    if (it.getString("login") == login && it.getString("password") == password) {
                        val userid = it.getString("id").toInt()
                        val status = it.getString("is_admin") == "t"
                        sharedPreferences.edit().putInt("USER_ID", userid).apply()
                        sharedPreferences.edit().putBoolean("USER_STATUS", status).apply()
                        return@withContext true
                    }
                }
            }
            return@withContext false
        }
}