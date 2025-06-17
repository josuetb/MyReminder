package com.myreminder.controllers

import com.myreminder.models.entities.Reminder
import com.myreminder.services.ReminderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reminders")
class ReminderController(private val service: ReminderService) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int) = service.getById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody reminder: Reminder) = ResponseEntity.ok(service.create(reminder))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody reminder: Reminder) = service.update(id, reminder)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
