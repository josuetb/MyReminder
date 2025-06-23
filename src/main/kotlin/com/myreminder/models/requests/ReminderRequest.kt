
package com.myreminder.models.requests

data class ReminderRequest(
    val userId: String,
    val subjectId: Int,
    val title: String,
    val description: String?,
    val date: String,
    val time: String,
    val priority: String,
    val priorityColor: String,
    val type: String?,
    val status: String,
    val notificationId: Int?
)
