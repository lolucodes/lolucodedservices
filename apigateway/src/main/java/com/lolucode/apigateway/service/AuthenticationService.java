package com.lolucode.apigateway.service;

import com.lolucode.apigateway.model.User;

public interface AuthenticationService
{
    User signInAndReturnJWT(User signInRequest);
}
