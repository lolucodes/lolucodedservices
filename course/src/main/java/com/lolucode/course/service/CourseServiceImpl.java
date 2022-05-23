package com.lolucode.course.service;

import com.lolucode.course.model.Course;
import com.lolucode.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course saveCourse(Course course) {
        course.setCreateTime(LocalDateTime.now());
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }
    @Override
    public List<Course> findCourseById(Long courseId) {
        return courseRepository.findCourseById(courseId);
    }
}
