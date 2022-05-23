package com.lolucode.apigateway.service;

import com.lolucode.apigateway.model.Role;
import com.lolucode.apigateway.model.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void changeRole(Role newRole, String username);
}
