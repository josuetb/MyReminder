package com.myreminder.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Subject(
    @Id
    val id: Int,
    val name: String,
    val userId: String
)
