package com.lolucode.apigateway.controller;

import com.lolucode.apigateway.request.EnrollmentServiceRequest;
import com.lolucode.apigateway.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gateway/enroll")//pre-path
public class EnrollmentController {

    @Autowired
    private EnrollmentServiceRequest enrollmentServiceRequest;

    @PostMapping//gateway/enroll
    public ResponseEntity<?> enrolInCourse(@RequestBody Object enrollment) {
        return new ResponseEntity<>(enrollmentServiceRequest.enrollInCourse(enrollment), HttpStatus.CREATED);
    }

    @GetMapping //gateway/enroll
    public ResponseEntity<?> getAllEnrollmentOfAuthorizedUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(enrollmentServiceRequest.getAllEnrollmentOfUser(userPrincipal.getId()));
    }


}

