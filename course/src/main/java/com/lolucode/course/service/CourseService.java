package com.lolucode.course.service;

import com.lolucode.course.model.Course;

import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);

    void deleteCourse(Long courseId);

    List<Course> findAllCourses();

    List<Course> findCourseById(Long courseId);

}
