package com.myreminder.services

import com.myreminder.models.entities.Subject
import com.myreminder.repositories.SubjectRepository
import com.myreminder.services.SubjectService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import java.util.*


class SubjectServiceTest {
    private lateinit var subjectRepository: SubjectRepository
    private lateinit var subjectService: SubjectService

    @BeforeEach
    fun setUp() {
        subjectRepository = mock()
        subjectService = SubjectService(subjectRepository)
    }

    // Prueba para obtener todas las materias
    @Test
    fun `should get all subjects correctly`() {
        val subjects = listOf(
            Subject(1, "Matemáticas", "u1"),
            Subject(2, "Historia", "u2")
        )
        `when`(subjectRepository.findAll()).thenReturn(subjects)
        val result = subjectService.getAll()
        assertEquals(2, result.size)
        assertEquals("Matemáticas", result[0].name)
    }

    // Prueba para obtener una materia por ID cuando existe
    @Test
    fun `should get a subject by ID if it exists`() {
        val subject = Subject(1, "Matemáticas", "u1")
        `when`(subjectRepository.findById(1)).thenReturn(Optional.of(subject))
        val result = subjectService.getById(1)
        assertNotNull(result)
        assertEquals("Matemáticas", result?.name)
    }

    // Prueba para obtener una materia por ID cuando NO existe
    @Test
    fun `should return null if subject does not exist by ID`() {
        `when`(subjectRepository.findById(99)).thenReturn(Optional.empty())
        val result = subjectService.getById(99)
        assertNull(result)
    }

    // Prueba para crear una materia
    @Test
    fun `should create a subject correctly`() {
        val subject = Subject(1, "Matemáticas", "u1")
        `when`(subjectRepository.save(any())).thenReturn(subject)
        val result = subjectService.create(subject)
        assertEquals("Matemáticas", result.name)
    }

    // Prueba para actualizar una materia cuando existe
    @Test
    fun `should update a subject if it exists`() {
        val subject = Subject(1, "Matemáticas", "u1")
        `when`(subjectRepository.existsById(1)).thenReturn(true)
        `when`(subjectRepository.save(subject)).thenReturn(subject)
        val result = subjectService.update(1, subject)
        assertNotNull(result)
        assertEquals("Matemáticas", result?.name)
    }

    // Prueba para actualizar una materia cuando NO existe
    @Test
    fun `should return null when updating if subject does not exist`() {
        val subject = Subject(1, "Matemáticas", "u1")
        `when`(subjectRepository.existsById(1)).thenReturn(false)
        val result = subjectService.update(1, subject)
        assertNull(result)
    }

    // Prueba para eliminar una materia
    @Test
    fun `should delete a subject correctly`() {
        doNothing().`when`(subjectRepository).deleteById(1)
        subjectService.delete(1)
        verify(subjectRepository, times(1)).deleteById(1)
    }
} 