package com.lolucode.course.repository;

import com.lolucode.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long>
{
    //findBy+fieldName

    List<Course> findCourseById(Long courseId);


    @Query("SELECT c FROM Course c WHERE CONCAT(c.title, c.subtitle) LIKE %?1%")
    public List<Course> search(String keyword);
}
