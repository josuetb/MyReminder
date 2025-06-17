
package com.myreminder.mappers

import com.myreminder.models.entities.Subject
import com.myreminder.models.requests.SubjectRequest
import com.myreminder.models.responses.SubjectResponse

object SubjectMapper {
    fun toEntity(id: Int = 0, request: SubjectRequest) = Subject(
        id = id,
        userId = request.userId,
        name = request.name
    )

    fun toResponse(subject: Subject) = SubjectResponse(
        id = subject.id,
        userId = subject.userId,
        name = subject.name
    )
}
