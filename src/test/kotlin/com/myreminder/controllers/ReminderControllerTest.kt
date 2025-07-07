package com.myreminder.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.myreminder.controllers.ReminderController
import com.myreminder.mappers.ReminderMapper
import com.myreminder.models.entities.Reminder
import com.myreminder.models.responses.ReminderResponse
import com.myreminder.routes.Routes
import com.myreminder.services.ReminderService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import org.springframework.test.web.servlet.delete
import java.time.LocalDate
import org.junit.jupiter.api.Assertions.assertEquals

@WebMvcTest(ReminderController::class)
@Import(MockConfig::class)
class ReminderControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var reminderService: ReminderService

    @Autowired
    private lateinit var reminderMapper: ReminderMapper

    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        objectMapper = ObjectMapper()
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

    val BASE_URL = Routes.REMINDERS

    @Test
    fun should_return_reminder_when_get_by_id() {
        val reminder = Reminder(
            id = 1,
            userId = "u1",
            subjectId = 1,
            title = "Examen",
            description = "Parcial de matemáticas",
            date = LocalDate.of(2024, 6, 10),
            time = "10:00",
            priority = "Alta",
            priorityColor = "Rojo",
            type = "Examen",
            status = "Pendiente",
            notificationId = 100
        )
        val response = ReminderResponse(
            id = 1,
            userId = "u1",
            subjectId = 1,
            title = "Examen",
            description = "Parcial de matemáticas",
            date = "2024-06-10",
            time = "10:00",
            priority = "Alta",
            priorityColor = "Rojo",
            type = "Examen",
            status = "Pendiente",
            notificationId = 100
        )
        `when`(reminderService.getById(1)).thenReturn(reminder)
        `when`(reminderMapper.toResponse(reminder)).thenReturn(response)

        val result = mockMvc.get("$BASE_URL/1")
            .andExpect {
                status { isOk() }
                jsonPath("$.title") { value("Examen") }
                jsonPath("$.priority") { value("Alta") }
            }.andReturn()
        assertEquals(200, result.response.status)
    }

    @Test
    fun should_return_404_when_get_a_non_existent_id() {
        `when`(reminderService.getById(99)).thenReturn(null)
        val result = mockMvc.get("$BASE_URL/99")
            .andExpect {
                status { isNotFound() }
            }.andReturn()
        assertEquals(404, result.response.status)
    }

    @Test
    fun should_create_reminder_when_post() {
        val reminder = Reminder(
            id = 2,
            userId = "u2",
            subjectId = 2,
            title = "Tarea",
            description = "Tarea de historia",
            date = LocalDate.of(2024, 6, 11),
            time = "12:00",
            priority = "Media",
            priorityColor = "Amarillo",
            type = "Tarea",
            status = "Pendiente",
            notificationId = 101
        )
        val response = ReminderResponse(
            id = 2,
            userId = "u2",
            subjectId = 2,
            title = "Tarea",
            description = "Tarea de historia",
            date = "2024-06-11",
            time = "12:00",
            priority = "Media",
            priorityColor = "Amarillo",
            type = "Tarea",
            status = "Pendiente",
            notificationId = 101
        )
        val json = objectMapper.writeValueAsString(reminder)
        `when`(reminderService.create(reminder)).thenReturn(reminder)
        `when`(reminderMapper.toResponse(reminder)).thenReturn(response)

        val result = mockMvc.post(BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andExpect {
            status { isOk() }
            jsonPath("$.title") { value("Tarea") }
            jsonPath("$.priority") { value("Media") }
        }.andReturn()
        assertEquals(200, result.response.status)
    }

    @Test
    fun should_update_reminder_when_put() {
        val reminder = Reminder(
            id = 1,
            userId = "u1",
            subjectId = 1,
            title = "Examen Actualizado",
            description = "Parcial actualizado",
            date = LocalDate.of(2024, 6, 12),
            time = "11:00",
            priority = "Alta",
            priorityColor = "Rojo",
            type = "Examen",
            status = "Pendiente",
            notificationId = 100
        )
        val response = ReminderResponse(
            id = 1,
            userId = "u1",
            subjectId = 1,
            title = "Examen Actualizado",
            description = "Parcial actualizado",
            date = "2024-06-12",
            time = "11:00",
            priority = "Alta",
            priorityColor = "Rojo",
            type = "Examen",
            status = "Pendiente",
            notificationId = 100
        )
        val json = objectMapper.writeValueAsString(reminder)
        `when`(reminderService.update(1, reminder)).thenReturn(reminder)
        `when`(reminderMapper.toResponse(reminder)).thenReturn(response)

        val result = mockMvc.put("$BASE_URL/1") {
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andExpect {
            status { isOk() }
            jsonPath("$.title") { value("Examen Actualizado") }
        }.andReturn()
        assertEquals(200, result.response.status)
    }

    @Test
    fun should_return_404_when_update_non_existent_reminder() {
        val reminder = Reminder(
            id = 99,
            userId = "uX",
            subjectId = 99,
            title = "No existe",
            description = null,
            date = LocalDate.of(2024, 6, 15),
            time = "00:00",
            priority = "Baja",
            priorityColor = "Verde",
            type = null,
            status = "Pendiente",
            notificationId = null
        )
        val json = objectMapper.writeValueAsString(reminder)
        `when`(reminderService.update(99, reminder)).thenReturn(null)

        val result = mockMvc.put("$BASE_URL/99") {
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andExpect {
            status { isNotFound() }
        }.andReturn()
        assertEquals(404, result.response.status)
    }

    @Test
    fun should_delete_reminder_when_delete() {
        val result = mockMvc.delete("$BASE_URL/1")
            .andExpect {
                status { isNoContent() }
            }.andReturn()
        assertEquals(204, result.response.status)
    }
}

@TestConfiguration
class MockConfig {
    @Bean
    fun reminderService(): ReminderService = mock(ReminderService::class.java)
    @Bean
    fun reminderMapper(): ReminderMapper = mock(ReminderMapper::class.java)
} 