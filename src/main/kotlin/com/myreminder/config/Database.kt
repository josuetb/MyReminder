
package com.myreminder.config

import java.sql.Connection
import java.sql.DriverManager

object Database {
    private const val URL = "jdbc:postgresql://localhost:5432/postgres"
    private const val USER = "postgres"
    private const val PASSWORD = "1753"

    init {
        try {
            Class.forName("org.postgresql.Driver")
            println("PostgreSQL JDBC Driver Registered!")
        } catch (e: ClassNotFoundException) {
            println("Error al registrar el driver de PostgreSQL: ${'$'}{e.message}")
        }
    }

    fun getConnection(): Connection? {
        return try {
            DriverManager.getConnection(URL, USER, PASSWORD)
        } catch (e: Exception) {
            println("Error al conectar a la base de datos: ${'$'}{e.message}")
            null
        }
    }
}
