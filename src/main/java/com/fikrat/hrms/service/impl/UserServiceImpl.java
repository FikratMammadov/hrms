package com.fikrat.hrms.service.impl;

import com.fikrat.hrms.model.User;
import com.fikrat.hrms.repository.UserRepository;
import com.fikrat.hrms.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<User> getList() {
        return userRepository.findAll();
    }
}
