package com.lolucode.enrollment.service;

import com.lolucode.enrollment.model.Enrollment;

import java.util.List;

public interface EnrollmentService {

    Enrollment enrollInCourse(Enrollment enrollment);

    List<Enrollment> viewAllEnrollmentsOfUser(Long userId);
}
