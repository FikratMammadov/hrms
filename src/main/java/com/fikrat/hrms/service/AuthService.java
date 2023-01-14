package com.fikrat.hrms.service;

import com.fikrat.hrms.dto.auth.JwtResponse;
import com.fikrat.hrms.dto.auth.LoginRequest;
import com.fikrat.hrms.dto.auth.RegisterRequest;
import com.fikrat.hrms.model.User;

public interface AuthService {
    JwtResponse login(LoginRequest request);

    User register(RegisterRequest request);
}
