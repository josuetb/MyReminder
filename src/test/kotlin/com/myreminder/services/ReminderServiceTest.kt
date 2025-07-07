package com.myreminder.services

import com.myreminder.models.entities.Reminder
import com.myreminder.repositories.ReminderRepository
import com.myreminder.services.ReminderService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import java.time.LocalDate
import java.util.*

class ReminderServiceTest {
    private lateinit var reminderRepository: ReminderRepository
    private lateinit var reminderService: ReminderService

    @BeforeEach
    fun setUp() {
        reminderRepository = mock()
        reminderService = ReminderService(reminderRepository)
    }

    @Test
    fun `should get all reminders correctly`() {
        val reminders = listOf(
            Reminder(1, "u1", 1, "Examen", "desc", LocalDate.now(), "10:00", "Alta", "Rojo", "Examen", "Pendiente", 100),
            Reminder(2, "u2", 2, "Tarea", "desc2", LocalDate.now(), "12:00", "Media", "Amarillo", "Tarea", "Pendiente", 101)
        )
        `when`(reminderRepository.findAll()).thenReturn(reminders)
        val result = reminderService.getAll()
        assertEquals(2, result.size)
        assertEquals("Examen", result[0].title)
    }

    @Test
    fun `should get a reminder by ID if it exists`() {
        val reminder = Reminder(1, "u1", 1, "Examen", "desc", LocalDate.now(), "10:00", "Alta", "Rojo", "Examen", "Pendiente", 100)
        `when`(reminderRepository.findById(1)).thenReturn(Optional.of(reminder))
        val result = reminderService.getById(1)
        assertNotNull(result)
        assertEquals("Examen", result?.title)
    }

    @Test
    fun `should return null if reminder does not exist by ID`() {
        `when`(reminderRepository.findById(99)).thenReturn(Optional.empty())
        val result = reminderService.getById(99)
        assertNull(result)
    }

    @Test
    fun `should create a reminder correctly`() {
        val reminder = Reminder(1, "u1", 1, "Examen", "desc", LocalDate.now(), "10:00", "Alta", "Rojo", "Examen", "Pendiente", 100)
        `when`(reminderRepository.save(any())).thenReturn(reminder)
        val result = reminderService.create(reminder)
        assertEquals("Examen", result.title)
    }

    @Test
    fun `should update a reminder if it exists`() {
        val reminder = Reminder(1, "u1", 1, "Examen", "desc", LocalDate.now(), "10:00", "Alta", "Rojo", "Examen", "Pendiente", 100)
        `when`(reminderRepository.existsById(1)).thenReturn(true)
        `when`(reminderRepository.save(reminder)).thenReturn(reminder)
        val result = reminderService.update(1, reminder)
        assertNotNull(result)
        assertEquals("Examen", result?.title)
    }

    @Test
    fun `should return null when updating if reminder does not exist`() {
        val reminder = Reminder(1, "u1", 1, "Examen", "desc", LocalDate.now(), "10:00", "Alta", "Rojo", "Examen", "Pendiente", 100)
        `when`(reminderRepository.existsById(1)).thenReturn(false)
        val result = reminderService.update(1, reminder)
        assertNull(result)
    }

    @Test
    fun `should delete a reminder correctly`() {
        doNothing().`when`(reminderRepository).deleteById(1)
        reminderService.delete(1)
        verify(reminderRepository, times(1)).deleteById(1)
    }
} 