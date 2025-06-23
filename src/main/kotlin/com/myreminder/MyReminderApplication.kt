package com.myreminder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyReminderApplication

fun main(args: Array<String>) {
    runApplication<MyReminderApplication>(*args)
} 