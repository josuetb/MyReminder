
package com.myreminder.mappers

import com.myreminder.models.entities.Reminder
import com.myreminder.models.requests.ReminderRequest
import com.myreminder.models.responses.ReminderResponse
import java.time.LocalDate

object ReminderMapper {
    fun toEntity(id: Int = 0, request: ReminderRequest) = Reminder(
        id = id,
        userId = request.userId,
        subjectId = request.subjectId,
        title = request.title,
        description = request.description,
        date = LocalDate.parse(request.date),
        time = request.time,
        priority = request.priority,
        priorityColor = request.priorityColor,
        type = request.type,
        status = request.status,
        notificationId = request.notificationId
    )

    fun toResponse(reminder: Reminder) = ReminderResponse(
        id = reminder.id,
        userId = reminder.userId,
        subjectId = reminder.subjectId,
        title = reminder.title,
        description = reminder.description,
        date = reminder.date.toString(),
        time = reminder.time,
        priority = reminder.priority,
        priorityColor = reminder.priorityColor,
        type = reminder.type,
        status = reminder.status,
        notificationId = reminder.notificationId
    )
}
