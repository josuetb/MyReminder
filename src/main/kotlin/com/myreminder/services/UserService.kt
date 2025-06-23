package com.myreminder.services

import com.myreminder.models.entities.User
import com.myreminder.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {
    fun getAll(): List<User> = repository.findAll()
    fun getById(id: String): User? = repository.findById(id).orElse(null)
    fun create(user: User): User = repository.save(user)
    fun update(id: String, user: User): User? {
        return if (repository.existsById(id)) repository.save(user) else null
    }
    fun delete(id: String) = repository.deleteById(id)
}
