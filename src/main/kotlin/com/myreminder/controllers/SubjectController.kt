package com.myreminder.controllers

import com.myreminder.mappers.SubjectMapper
import com.myreminder.models.requests.SubjectRequest
import com.myreminder.models.responses.SubjectResponse
import com.myreminder.services.SubjectService

class SubjectController(private val service: SubjectService) {
    fun getAll(): List<SubjectResponse> =
        service.getAll().map { SubjectMapper.toResponse(it) }

    fun getById(id: Int): SubjectResponse? =
        service.getById(id)?.let { SubjectMapper.toResponse(it) }

    fun getByUser(userId: String): List<SubjectResponse> =
        service.getByUser(userId).map { SubjectMapper.toResponse(it) }

    fun create(request: SubjectRequest): SubjectResponse =
        SubjectMapper.toResponse(service.create(SubjectMapper.toEntity(0, request)))

    fun delete(id: Int) = service.delete(id)

    fun update(id: Int, request: SubjectRequest): SubjectResponse = service.update(id, request.toSubject()).toResponse()
}
