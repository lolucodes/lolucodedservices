package com.lolucode.enrollment.repository;

import com.lolucode.enrollment.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    //findBy+fieldName

    List<Enrollment> findAllByUserId(Long userId);
}
