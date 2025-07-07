package com.myreminder.exceptions.handlers

import com.myreminder.exceptions.exceptions.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException): ResponseEntity<Map<String, String>> =
        ResponseEntity(mapOf("error" to (ex.message ?: "Not found")), HttpStatus.NOT_FOUND)

    @ExceptionHandler(Exception::class)
    fun handleGeneric(ex: Exception): ResponseEntity<Map<String, String>> =
        ResponseEntity(mapOf("error" to (ex.message ?: "Unexpected error")), HttpStatus.INTERNAL_SERVER_ERROR)
}
