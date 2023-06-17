package com.example.bootm2m.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    @ManyToMany(mappedBy = "enrolledIn")
    val studentsEnrolled: List<Student> = listOf()
)

data class ViewCourse(
    val id: Long,
    val name: String,
    val students: List<String>
)

fun Course.toView(): ViewCourse =
    ViewCourse(id = this.id, name = this.name, students = studentsEnrolled.map { it.name })

data class AddCourse(
    val name: String
)