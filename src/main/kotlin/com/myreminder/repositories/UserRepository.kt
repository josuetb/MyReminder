package com.myreminder.repositories

import com.myreminder.models.entities.User

interface UserRepository {
    fun findAll(): List<User>
    fun findById(uid: String): User?
    fun save(user: User): User
    fun delete(uid: String)
    fun update(uid: String, user: User): User
}
