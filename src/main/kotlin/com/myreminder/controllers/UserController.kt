package com.myreminder.controllers

import com.myreminder.models.entities.User
import com.myreminder.services.UserService
import com.myreminder.mappers.UserMapper
import com.myreminder.routes.Routes
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Routes.USERS)
class UserController(private val service: UserService, private val mapper: UserMapper) {
    @GetMapping
    fun getAll() = service.getAll().map { mapper.toResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String) = service.getById(id)?.let { ResponseEntity.ok(mapper.toResponse(it)) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody user: User) = ResponseEntity.ok(mapper.toResponse(service.create(user)))

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody user: User) = service.update(id, user)?.let { ResponseEntity.ok(mapper.toResponse(it)) } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
