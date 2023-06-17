package com.example.bootm2m.repository

import com.example.bootm2m.models.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepo : CrudRepository<Course, Long>