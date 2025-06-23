package com.myreminder.mappers

import com.myreminder.models.entities.Subject
import com.myreminder.models.responses.SubjectResponse
import org.springframework.stereotype.Component

@Component
class SubjectMapper {
    fun toResponse(subject: Subject): SubjectResponse {
        return SubjectResponse(
            id = subject.id,
            name = subject.name,
            userId = subject.userId
        )
    }
} 