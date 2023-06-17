package com.example.bootm2m.controller

import com.example.bootm2m.models.AddCourse
import com.example.bootm2m.models.AddStudent
import com.example.bootm2m.models.Course
import com.example.bootm2m.models.EnrollCourseForStudent
import com.example.bootm2m.models.Student
import com.example.bootm2m.models.ViewCourse
import com.example.bootm2m.models.ViewStudent
import com.example.bootm2m.models.toView
import com.example.bootm2m.repository.CourseRepo
import com.example.bootm2m.repository.StudentRepo
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("student")
class StudentController(
    val studentRepo: StudentRepo,
    val courseRepo: CourseRepo
) {

    @GetMapping("/")
    fun getAll(): List<ViewStudent> {
        return studentRepo.findAll().map { it.toView() }
    }

    @PostMapping("/")
    fun addStudent(@RequestBody request: AddStudent): ViewStudent {
        return studentRepo.save(Student(name = request.name)).toView()
    }

    @PostMapping("{id}/enroll")
    fun enrollStudent(@PathVariable id: Long, @RequestBody courseId: EnrollCourseForStudent): ViewStudent {
        val student = studentRepo.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        val course =
            courseRepo.findById(courseId.courseId).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        return studentRepo.save(student.copy(enrolledIn = student.enrolledIn.plus(course))).toView()
    }
}