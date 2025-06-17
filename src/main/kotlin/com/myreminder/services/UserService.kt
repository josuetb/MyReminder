package com.myreminder.services

import com.myreminder.models.entities.User
import com.myreminder.repositories.UserRepository

class UserService(private val repository: UserRepository) {
    fun getAll(): List<User> = repository.findAll()
    fun getById(uid: String): User? = repository.findById(uid)
    fun create(user: User): User = repository.save(user)
    fun delete(uid: String) = repository.delete(uid)
    fun update(uid: String, user: User): User = repository.update(uid, user)
}
