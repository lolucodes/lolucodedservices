package com.lolucode.apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "enrollment-service", //microservice-application-name
        path = "api/enroll", //pre-path for service endpoints
        //url = "${enrollment.service.url}",
        configuration = FeignConfiguration.class)
public interface EnrollmentServiceRequest
{
    @PostMapping//api/enroll
    Object enrollInCourse(@RequestBody Object requestBody);

    @GetMapping("{userId}")//api/enroll/{userId}
    List<Object> getAllEnrollmentOfUser(@PathVariable("userId") Long userId);

}
