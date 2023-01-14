package com.fikrat.hrms.service.impl;

import com.fikrat.hrms.constants.Messages;
import com.fikrat.hrms.dto.employee.AddEmployeeDto;
import com.fikrat.hrms.dto.employee.EmployeeRequestDto;
import com.fikrat.hrms.exception.EmployeeAlreadyExistsException;
import com.fikrat.hrms.exception.ManagerNotFoundException;
import com.fikrat.hrms.exception.UserIsAdminException;
import com.fikrat.hrms.exception.UserNotFoundException;
import com.fikrat.hrms.model.Employee;
import com.fikrat.hrms.model.Manager;
import com.fikrat.hrms.model.Role;
import com.fikrat.hrms.model.User;
import com.fikrat.hrms.model.enums.ERole;
import com.fikrat.hrms.repository.EmployeeRepository;
import com.fikrat.hrms.repository.ManagerRepository;
import com.fikrat.hrms.repository.RoleRepository;
import com.fikrat.hrms.repository.UserRepository;
import com.fikrat.hrms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Employee add(AddEmployeeDto dto) {
        log.info("ActionLog.addEmployee.start");
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND));

        checkEmployeeExists(user);
        checkAdmin(user);

        Manager manager = managerRepository.findById(dto.getManagerId())
                .orElseThrow(() -> new ManagerNotFoundException(Messages.MANAGER_NOT_FOUND));

        Employee employee = Employee.builder()
                .user(user)
                .manager(manager)
                .build();

        log.info("ActionLog.addEmployee.end");
        return employeeRepository.save(employee);
    }

    @Override
    public Employee add(Principal principal, EmployeeRequestDto employeeDto) {
        log.info("ActionLog.addEmployee.start");
        User user = userRepository.findById(employeeDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND));

        checkEmployeeExists(user);
        checkAdmin(user);

        User managerUser = userRepository.findByUsername(principal.getName()).orElseThrow(() ->
                new UsernameNotFoundException("Username not found with name " + principal.getName()));

        Manager manager = managerRepository.findByUserId(managerUser.getId());

        Employee employee = Employee.builder()
                .user(user)
                .manager(manager)
                .build();

        log.info("ActionLog.addEmployee.end");
        return employeeRepository.save(employee);
    }

    private void checkEmployeeExists(User user) {
        Optional<Employee> employee = employeeRepository.findByUserId(user.getId());
        if (employee.isPresent()) {
            log.error("this user is already an employee, userId={}", user.getId());
            throw new EmployeeAlreadyExistsException(Messages.EMPLOYEE_ALREADY_EXISTS);
        }
    }

    private void checkAdmin(User user) {
        Role role = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElse(Role.builder().name(ERole.ROLE_ADMIN).build());

        if (user.getRoles().contains(role)) {
            log.error("this user is admin, admin = {}", user.getUsername());
            throw new UserIsAdminException(Messages.ADMIN_CAN_NOT_BE_EMPLOYEE);
        }
    }
}
