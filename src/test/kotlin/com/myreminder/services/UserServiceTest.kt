package com.myreminder.services

import com.myreminder.models.entities.User
import com.myreminder.repositories.UserRepository
import com.myreminder.services.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import java.util.*

class UserServiceTest {
    private lateinit var userRepository: UserRepository
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userRepository = mock()
        userService = UserService(userRepository)
    }

    @Test
    fun `should get all users correctly`() {
        val users = listOf(
            User("u1", "Josue", "josue@gmail.com"),
            User("u2", "Emma", "emma@gmail.com")
        )
        `when`(userRepository.findAll()).thenReturn(users)
        val result = userService.getAll()
        assertEquals(2, result.size)
        assertEquals("Josue", result[0].name)
    }

    @Test
    fun `should get a user by ID if it exists`() {
        val user = User("u1", "Josue", "josue@gmail.com")
        `when`(userRepository.findById("u1")).thenReturn(Optional.of(user))
        val result = userService.getById("u1")
        assertNotNull(result)
        assertEquals("Josue", result?.name)
    }

    @Test
    fun `should return null if user does not exist by ID`() {
        `when`(userRepository.findById("u99")).thenReturn(Optional.empty())
        val result = userService.getById("u99")
        assertNull(result)
    }

    @Test
    fun `should create a user correctly`() {
        val user = User("u1", "Josue", "josue@gmail.com")
        `when`(userRepository.save(any())).thenReturn(user)
        val result = userService.create(user)
        assertEquals("Josue", result.name)
    }

    @Test
    fun `should update a user if it exists`() {
        val user = User("u1", "Josue", "josue@gmail.com")
        `when`(userRepository.existsById("u1")).thenReturn(true)
        `when`(userRepository.save(user)).thenReturn(user)
        val result = userService.update("u1", user)
        assertNotNull(result)
        assertEquals("Josue", result?.name)
    }

    @Test
    fun `should return null when updating if user does not exist`() {
        val user = User("u1", "Josue", "josue@gmail.com")
        `when`(userRepository.existsById("u1")).thenReturn(false)
        val result = userService.update("u1", user)
        assertNull(result)
    }

    @Test
    fun `should delete a user correctly`() {
        doNothing().`when`(userRepository).deleteById("u1")
        userService.delete("u1")
        verify(userRepository, times(1)).deleteById("u1")
    }
} 