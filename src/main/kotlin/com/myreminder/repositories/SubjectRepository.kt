package com.myreminder.repositories

import com.myreminder.models.entities.Subject

interface SubjectRepository {
    fun findAll(): List<Subject>
    fun findById(id: Int): Subject?
    fun findByUser(userId: String): List<Subject>
    fun save(subject: Subject): Subject
    fun delete(id: Int)
    fun update(id: Int, subject: Subject): Subject
}
