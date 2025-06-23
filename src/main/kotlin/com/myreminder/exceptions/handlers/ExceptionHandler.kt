
package com.myreminder.exceptions.handlers

import com.myreminder.exceptions.exceptions.NotFoundException

object ExceptionHandler {
    fun handle(exception: Exception): String {
        return when (exception) {
            is NotFoundException -> "Not Found: " + exception.message
            else -> "Internal Error: " + exception.message
        }
    }
}
