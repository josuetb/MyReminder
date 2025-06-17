package com.myreminder.controllers

import com.myreminder.mappers.UserMapper
import com.myreminder.models.requests.UserRequest
import com.myreminder.models.responses.UserResponse
import com.myreminder.services.UserService

class UserController(private val service: UserService) {
    fun getAll(): List<UserResponse> =
        service.getAll().map { UserMapper.toResponse(it) }

    fun getById(uid: String): UserResponse? =
        service.getById(uid)?.let { UserMapper.toResponse(it) }

    fun create(request: UserRequest): UserResponse =
        UserMapper.toResponse(service.create(UserMapper.toEntity(request)))

    fun delete(uid: String) = service.delete(uid)

    fun update(uid: String, request: UserRequest): UserResponse = service.update(uid, request.toUser()).toResponse()
}
