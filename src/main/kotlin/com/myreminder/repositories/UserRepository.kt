package com.myreminder.repositories

import com.myreminder.models.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String>
