package com.myreminder.mappers

import com.myreminder.models.entities.User
import com.myreminder.models.responses.UserResponse
import com.myreminder.models.responses.UserSummaryResponse
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toSummary(user: User): UserSummaryResponse {
        return UserSummaryResponse(
            uid = user.id,
            name = user.name,
            email = user.email
        )
    }

    fun toResponse(user: User): UserResponse {
        return UserResponse(
            uid = user.id,
            name = user.name,
            email = user.email
        )
    }
} 