package com.myreminder.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.myreminder.controllers.UserController
import com.myreminder.mappers.UserMapper
import com.myreminder.models.entities.User
import com.myreminder.models.responses.UserResponse
import com.myreminder.routes.Routes
import com.myreminder.services.UserService
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

@WebMvcTest(UserController::class)
@Import(MockConfigUser::class)
class UserControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userMapper: UserMapper

    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        objectMapper = ObjectMapper()
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

    val BASE_URL = Routes.USERS

    @Test
    fun should_return_user_when_get_by_id() {
        val user = User("u1", "Josue", "josue@gmail.com")
        val response = UserResponse("u1", "Josue", "josue@gmail.com")
        `when`(userService.getById("u1")).thenReturn(user)
        `when`(userMapper.toResponse(user)).thenReturn(response)

        val result = mockMvc.get("$BASE_URL/u1")
            .andExpect {
                status { isOk() }
                jsonPath("$.name") { value("Josue") }
                jsonPath("$.email") { value("josue@gmail.com") }
            }.andReturn()
        assertEquals(200, result.response.status)
    }

    @Test
    fun should_return_404_when_get_a_non_existent_id() {
        `when`(userService.getById("u99")).thenReturn(null)
        val result = mockMvc.get("$BASE_URL/u99")
            .andExpect {
                status { isNotFound() }
            }.andReturn()
        assertEquals(404, result.response.status)
    }

    @Test
    fun should_create_user_when_post() {
        val user = User("u2", "Emma", "emma@gmail.com")
        val response = UserResponse("u2", "Emma", "emma@gmail.com")
        val json = objectMapper.writeValueAsString(user)
        `when`(userService.create(user)).thenReturn(user)
        `when`(userMapper.toResponse(user)).thenReturn(response)

        val result = mockMvc.post(BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andExpect {
            status { isOk() }
            jsonPath("$.name") { value("Emma") }
        }.andReturn()
        assertEquals(200, result.response.status)
    }

    @Test
    fun should_update_user_when_put() {
        val user = User("u1", "Josue", "josue@gmail.com")
        val response = UserResponse("u1", "Josue", "josue@gmail.com")
        val json = objectMapper.writeValueAsString(user)
        `when`(userService.update("u1", user)).thenReturn(user)
        `when`(userMapper.toResponse(user)).thenReturn(response)

        val result = mockMvc.put("$BASE_URL/u1") {
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andExpect {
            status { isOk() }
            jsonPath("$.name") { value("Josue") }
        }.andReturn()
        assertEquals(200, result.response.status)
    }

    @Test
    fun should_return_404_when_update_non_existent_user() {
        val user = User("u99", "No existe", "noexiste@gmail.com")
        val json = objectMapper.writeValueAsString(user)
        `when`(userService.update("u99", user)).thenReturn(null)

        val result = mockMvc.put("$BASE_URL/u99") {
            contentType = MediaType.APPLICATION_JSON
            content = json
        }.andExpect {
            status { isNotFound() }
        }.andReturn()
        assertEquals(404, result.response.status)
    }

    @Test
    fun should_delete_user_when_delete() {
        val result = mockMvc.delete("$BASE_URL/u1")
            .andExpect {
                status { isNoContent() }
            }.andReturn()
        assertEquals(204, result.response.status)
    }
}

@TestConfiguration
class MockConfigUser {
    @Bean
    fun userService(): UserService = mock(UserService::class.java)
    @Bean
    fun userMapper(): UserMapper = mock(UserMapper::class.java)
} 