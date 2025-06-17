package com.myreminder.controllers

import com.myreminder.mappers.ReminderMapper
import com.myreminder.models.requests.ReminderRequest
import com.myreminder.models.responses.ReminderResponse
import com.myreminder.services.ReminderService

class ReminderController(private val service: ReminderService) {
    fun getAll(): List<ReminderResponse> =
        service.getAll().map { ReminderMapper.toResponse(it) }

    fun getById(id: Int): ReminderResponse? =
        service.getById(id)?.let { ReminderMapper.toResponse(it) }

    fun getByUser(userId: String): List<ReminderResponse> =
        service.getByUser(userId).map { ReminderMapper.toResponse(it) }

    fun getBySubject(subjectId: Int): List<ReminderResponse> =
        service.getBySubject(subjectId).map { ReminderMapper.toResponse(it) }

    fun create(request: ReminderRequest): ReminderResponse =
        ReminderMapper.toResponse(service.create(ReminderMapper.toEntity(0, request)))

    fun delete(id: Int) = service.delete(id)

    fun update(id: Int, request: ReminderRequest): ReminderResponse = service.update(id, request.toReminder()).toResponse()
}
