package com.example.bootm2m.controller

import com.example.bootm2m.models.AddCourse
import com.example.bootm2m.models.Course
import com.example.bootm2m.models.ViewCourse
import com.example.bootm2m.models.toView
import com.example.bootm2m.repository.CourseRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("course")
class CourseController(
    val courseRepo: CourseRepo
) {

    @GetMapping("/")
    fun getAll(): List<ViewCourse> {
        return courseRepo.findAll().map { it.toView() }
    }

    @PostMapping("/")
    fun addCourse(@RequestBody request: AddCourse): ViewCourse {
        return courseRepo.save(Course(name = request.name)).toView()
    }
}