package com.myreminder.controllers

import com.myreminder.models.entities.Subject
import com.myreminder.services.SubjectService
import com.myreminder.mappers.SubjectMapper
import com.myreminder.routes.Routes
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Routes.SUBJECTS)
class SubjectController(private val service: SubjectService, private val mapper: SubjectMapper) {
    @GetMapping
    fun getAll() = service.getAll().map { mapper.toResponse(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int) = service.getById(id)?.let { ResponseEntity.ok(mapper.toResponse(it)) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody subject: Subject) = ResponseEntity.ok(mapper.toResponse(service.create(subject)))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody subject: Subject) = service.update(id, subject)?.let { ResponseEntity.ok(mapper.toResponse(it)) } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
