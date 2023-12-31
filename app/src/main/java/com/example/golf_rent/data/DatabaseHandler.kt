package com.example.golf_rent.data

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DatabaseHandler {

    private var connection: Connection? = null

    private val host = "10.0.2.2"

    private val database = "golf"
    private val port = 5432
    private val user = "postgres"
    private val pass = ""
    private var url = "jdbc:postgresql://%s:%d/%s"


    suspend fun executeQuery(query: String): ResultSet? = suspendCoroutine { continuation ->
        val rs = null
        url = String.format(url, host, port, database)
        GlobalScope.launch {
            try {
                Class.forName("org.postgresql.Driver")
                connection = DriverManager.getConnection(url, user, pass)
                val statement = connection?.createStatement()
                continuation.resume(statement?.executeQuery(query))
            } catch (e: Exception) {
                Log.e("executeQuery" ,"${e.message}: ${e.cause}")
                continuation.resume(rs)
            }

        }

    }


}