package com.myreminder.repositories

import com.myreminder.models.entities.Reminder
import org.springframework.data.jpa.repository.JpaRepository

interface ReminderRepository : JpaRepository<Reminder, Int>
