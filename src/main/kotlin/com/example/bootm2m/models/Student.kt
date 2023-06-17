package com.example.bootm2m.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany

@Entity
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,

    @ManyToMany
    @JoinTable(
        name = "student_enrolled_in_course",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [(JoinColumn(name = "course_id"))]
    )
    val enrolledIn: List<Course> = listOf()
)

data class ViewStudent(
    val id: Long,
    val name: String,
    val courses: List<String>
)

fun Student.toView(): ViewStudent =
    ViewStudent(id = this.id, name = this.name, courses = this.enrolledIn.map { it.name })

data class AddStudent(
    val name: String
)

data class EnrollCourseForStudent(
    val courseId: Long
)