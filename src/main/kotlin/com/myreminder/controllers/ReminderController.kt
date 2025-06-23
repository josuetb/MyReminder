package com.myreminder.controllers

import com.myreminder.models.entities.Reminder
import com.myreminder.services.ReminderService
import com.myreminder.mappers.ReminderMapper
import com.myreminder.routes.Routes
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Routes.REMINDERS)
class ReminderController(private val service: ReminderService, private val mapper: ReminderMapper) {
    @GetMapping
    fun getAll() = service.getAll().map { mapper.toResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int) = service.getById(id)?.let { ResponseEntity.ok(mapper.toResponse(it)) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody reminder: Reminder) = ResponseEntity.ok(mapper.toResponse(service.create(reminder)))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody reminder: Reminder) = service.update(id, reminder)?.let { ResponseEntity.ok(mapper.toResponse(it)) } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
