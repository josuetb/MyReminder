package com.myreminder.services

import com.myreminder.models.entities.Subject
import com.myreminder.repositories.SubjectRepository
import org.springframework.stereotype.Service

@Service
class SubjectService(private val repository: SubjectRepository) {
    fun getAll(): List<Subject> = repository.findAll()
    fun getById(id: Int): Subject? = repository.findById(id).orElse(null)
    fun create(subject: Subject): Subject = repository.save(subject)
    fun update(id: Int, subject: Subject): Subject? {
        return if (repository.existsById(id)) repository.save(subject) else null
    }
    fun delete(id: Int) = repository.deleteById(id)
}
