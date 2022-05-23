package com.lolucode.enrollment.controller;

import com.lolucode.enrollment.model.Enrollment;
import com.lolucode.enrollment.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/enroll")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping //api/enroll
    public ResponseEntity<?> enrolInCourse(@RequestBody Enrollment enrollment) {
        return new ResponseEntity<>(enrollmentService.enrollInCourse(enrollment), HttpStatus.CREATED);
    }

    @GetMapping("{userId}")//api/enrollment/{userId}
    public ResponseEntity<?> getAllEnrollmentOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(enrollmentService.viewAllEnrollmentsOfUser(userId));
    }
}
