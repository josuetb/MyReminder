package com.myreminder.repositories

import com.myreminder.models.entities.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository : JpaRepository<Subject, Int>
