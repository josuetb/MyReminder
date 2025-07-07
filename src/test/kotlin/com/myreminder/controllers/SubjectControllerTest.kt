package com.myreminder.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.myreminder.controllers.SubjectController
import com.myreminder.mappers.SubjectMapper
import com.myreminder.models.entities.Subject
import com.myreminder.models.responses.SubjectResponse
import com.myreminder.routes.Routes
import com.myreminder.services.SubjectService
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
import org.junit.jupiter.api.Assertions.assertEquals

@WebMvcTest(SubjectController::class)
@Import(MockConfigSubject::class)
class SubjectControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var subjectService: SubjectService

    @Autowired
    private lateinit var subjectMapper: SubjectMapper

    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        objectMapper = ObjectMapper()
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

    val BASE_URL = Routes.SUBJECTS

    @Test
    fun should_return_subject_when_get_by_id() {
        val subject = Subject(1, "Matemáticas", "u1")
        val response = SubjectResponse(1, "u1", "Matemáticas")
        `when`(subjectService.getById(1)).thenReturn(subject)
        `when`(subjectMapper.toResponse(subject)).thenReturn(response)

        val result = mockMvc.get("$BASE_URL/1")
            .andExpect {
                status { isOk() }
                jsonPath("$.name") { value("Matemáticas") }
                jsonPath("$.userId") { value("u1") }
            }.andReturn()
        assertEquals(200, result.response.status)
    }

    @Test
    fun should_return_404_when_get_a_non_existent_id() {
        `when`(subjectService.getById(99)).thenReturn(null)
        val result = mockMvc.get("$BASE_URL/99")
            .andExpect {
                status { isNotFound() }
            }.andReturn()
        assertEquals(404, result.response.status)
    }

    @Test
    fun should_create_subject_when_post() {
        val subject = Subject(2, "Historia", "u2")
        val response = SubjectResponse(2, "u2", "Historia")
        val json = objectMapper.writeValueAsString(subject)
        `when`(subjectService.create(subject)).thenReturn(subject)
        `when`(subjectMapper.toResponse(subject)).thenReturn(response)

        val result = mockMvc.post(BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andExpect {
            status { isOk() }
            jsonPath("$.name") { value("Historia") }
        }.andReturn()
        assertEquals(200, result.response.status)
    }

    @Test
    fun should_update_subject_when_put() {
        val subject = Subject(1, "Matemáticas Actualizada", "u1")
        val response = SubjectResponse(1, "u1", "Matemáticas Actualizada")
        val json = objectMapper.writeValueAsString(subject)
        `when`(subjectService.update(1, subject)).thenReturn(subject)
        `when`(subjectMapper.toResponse(subject)).thenReturn(response)

        val result = mockMvc.put("$BASE_URL/1") {
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andExpect {
            status { isOk() }
            jsonPath("$.name") { value("Matemáticas Actualizada") }
        }.andReturn()
        assertEquals(200, result.response.status)
    }

    @Test
    fun should_return_404_when_update_non_existent_subject() {
        val subject = Subject(99, "No existe", "uX")
        val json = objectMapper.writeValueAsString(subject)
        `when`(subjectService.update(99, subject)).thenReturn(null)

        val result = mockMvc.put("$BASE_URL/99") {
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andExpect {
            status { isNotFound() }
        }.andReturn()
        assertEquals(404, result.response.status)
    }

    @Test
    fun should_delete_subject_when_delete() {
        val result = mockMvc.delete("$BASE_URL/1")
            .andExpect {
                status { isNoContent() }
            }.andReturn()
        assertEquals(204, result.response.status)
    }
}

@TestConfiguration
class MockConfigSubject {
    @Bean
    fun subjectService(): SubjectService = mock(SubjectService::class.java)
    @Bean
    fun subjectMapper(): SubjectMapper = mock(SubjectMapper::class.java)
} 