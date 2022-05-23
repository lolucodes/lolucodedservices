package com.lolucode.course.controller;

import com.lolucode.course.model.Course;
import com.lolucode.course.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/course")//pre-path
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping //api/course
    public ResponseEntity<?> saveCourse(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    @DeleteMapping("{courseId}")//api/course/{courseId}
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping //api/course
    public ResponseEntity<?> getAllCourses() {

        return ResponseEntity.ok(courseService.findAllCourses());
    }

    @GetMapping("{courseId}")//api/course/{courseId}
    public ResponseEntity<?> findCourseById(@PathVariable Long courseId) {
        return new ResponseEntity<>(courseService.findCourseById(courseId),
        HttpStatus.OK);
    }

}
