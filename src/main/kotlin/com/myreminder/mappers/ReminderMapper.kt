package com.myreminder.mappers

import com.myreminder.models.entities.Reminder
import com.myreminder.models.responses.ReminderResponse
import org.springframework.stereotype.Component

@Component
class ReminderMapper {
    fun toResponse(reminder: Reminder): ReminderResponse {
        return ReminderResponse(
            id = reminder.id,
            title = reminder.title,
            description = reminder.description,
            date = reminder.date.toString(),
            userId = reminder.userId,
            subjectId = reminder.subjectId,
            time = reminder.time,
            priority = reminder.priority,
            priorityColor = reminder.priorityColor,
            type = reminder.type,
            status = reminder.status,
            notificationId = reminder.notificationId
        )
    }
} 