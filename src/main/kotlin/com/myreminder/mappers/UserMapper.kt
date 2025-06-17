
package com.myreminder.mappers

import com.myreminder.models.entities.User
import com.myreminder.models.requests.UserRequest
import com.myreminder.models.responses.UserResponse

object UserMapper {
    fun toEntity(request: UserRequest) = User(
        uid = request.uid,
        name = request.name,
        email = request.email
    )

    fun toResponse(user: User) = UserResponse(
        uid = user.uid,
        name = user.name,
        email = user.email
    )
}
