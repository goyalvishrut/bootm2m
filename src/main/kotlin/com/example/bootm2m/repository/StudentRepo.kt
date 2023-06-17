package com.example.bootm2m.repository

import com.example.bootm2m.models.Student
import org.springframework.data.repository.CrudRepository

interface StudentRepo : CrudRepository<Student, Long>