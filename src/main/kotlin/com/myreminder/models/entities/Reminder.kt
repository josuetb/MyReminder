package com.myreminder.models.entities

import java.time.LocalDate
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Reminder(
    @Id
    val id: Int,
    val userId: String,
    val subjectId: Int,
    val title: String,
    val description: String?,
    val date: LocalDate,
    val time: String,
    val priority: String,
    val priorityColor: String,
    val type: String?,
    val status: String,
    val notificationId: Int?
)
