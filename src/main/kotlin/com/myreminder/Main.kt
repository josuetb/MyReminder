
package com.myreminder

import com.myreminder.controllers.UserController
import com.myreminder.dtos.UserDTO

fun main() {
    val userController = UserController()

    println("🎓 Bienvenido a MyReminder")
    println("➕ Creando usuario de prueba...")

    val user = UserDTO(
        uid = "u001",
        name = "Josue",
        email = "josue@example.com"
    )
    val createdUser = userController.createUser(user)
    println("✅ Usuario creado: $createdUser")

    println("\n📋 Listando usuarios:")
    val allUsers = userController.getAllUsers()
    allUsers.forEach { println(it) }
}
