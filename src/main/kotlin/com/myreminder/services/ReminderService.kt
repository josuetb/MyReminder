package com.myreminder.services

import com.myreminder.models.entities.Reminder
import com.myreminder.repositories.ReminderRepository
import org.springframework.stereotype.Service

@Service
class ReminderService(private val repository: ReminderRepository) {
    fun getAll(): List<Reminder> = repository.findAll()
    fun getById(id: Int): Reminder? = repository.findById(id).orElse(null)
    fun create(reminder: Reminder): Reminder = repository.save(reminder)
    fun update(id: Int, reminder: Reminder): Reminder? {
        return if (repository.existsById(id)) repository.save(reminder) else null
    }
    fun delete(id: Int) = repository.deleteById(id)
}
