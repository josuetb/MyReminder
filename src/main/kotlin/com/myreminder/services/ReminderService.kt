package com.myreminder.services

import com.myreminder.models.entities.Reminder
import com.myreminder.repositories.ReminderRepository

class ReminderService(private val repository: ReminderRepository) {
    fun getAll(): List<Reminder> = repository.findAll()
    fun getById(id: Int): Reminder? = repository.findById(id)
    fun getByUser(userId: String): List<Reminder> = repository.findByUser(userId)
    fun getBySubject(subjectId: Int): List<Reminder> = repository.findBySubject(subjectId)
    fun create(reminder: Reminder): Reminder = repository.save(reminder)
    fun delete(id: Int) = repository.delete(id)
    fun update(id: Int, reminder: Reminder): Reminder = repository.update(id, reminder)
}
