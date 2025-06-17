package com.myreminder.controllers

import com.myreminder.models.entities.Subject
import com.myreminder.services.SubjectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/subjects")
class SubjectController(private val service: SubjectService) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int) = service.getById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody subject: Subject) = ResponseEntity.ok(service.create(subject))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody subject: Subject) = service.update(id, subject)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
