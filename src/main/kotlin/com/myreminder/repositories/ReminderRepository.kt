package com.myreminder.repositories

import com.myreminder.models.entities.Reminder

interface ReminderRepository {
    fun findAll(): List<Reminder>
    fun findById(id: Int): Reminder?
    fun findByUser(userId: String): List<Reminder>
    fun findBySubject(subjectId: Int): List<Reminder>
    fun save(reminder: Reminder): Reminder
    fun delete(id: Int)
    fun update(id: Int, reminder: Reminder): Reminder
}
