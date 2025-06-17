
package com.myreminder.models.responses

data class ReminderResponse(
    val id: Int,
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
