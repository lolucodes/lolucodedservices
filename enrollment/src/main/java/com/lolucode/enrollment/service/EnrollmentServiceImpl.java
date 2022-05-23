package com.lolucode.enrollment.service;

import com.lolucode.enrollment.model.Enrollment;
import com.lolucode.enrollment.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment enrollInCourse(Enrollment enrollment) {
        enrollment.setEnrollmentTime(LocalDateTime.now());
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> viewAllEnrollmentsOfUser(Long userId) {
        return enrollmentRepository.findAllByUserId(userId);
    }
}
