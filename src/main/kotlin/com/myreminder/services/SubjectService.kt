package com.myreminder.services

import com.myreminder.models.entities.Subject
import com.myreminder.repositories.SubjectRepository

class SubjectService(private val repository: SubjectRepository) {
    fun getAll(): List<Subject> = repository.findAll()
    fun getById(id: Int): Subject? = repository.findById(id)
    fun getByUser(userId: String): List<Subject> = repository.findByUser(userId)
    fun create(subject: Subject): Subject = repository.save(subject)
    fun delete(id: Int) = repository.delete(id)
    fun update(id: Int, subject: Subject): Subject = repository.update(id, subject)
}
