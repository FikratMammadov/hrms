package com.fikrat.hrms.service.impl;

import com.fikrat.hrms.constants.Messages;
import com.fikrat.hrms.dto.manager.ManagerDto;
import com.fikrat.hrms.exception.ManagerAlreadyExistsException;
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
import com.fikrat.hrms.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Manager add(ManagerDto managerDto) {
        log.info("ActionLog.add.start");
        User user = userRepository.findById(managerDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND));

        if (managerRepository.existsManagerByUser(user)) {
            log.error("this user is already a manager, userId={}", user.getId());
            throw new ManagerAlreadyExistsException(Messages.MANAGER_ALREADY_EXISTS);
        }

        Role roleManager = roleRepository.findByName(ERole.ROLE_MANAGER)
                .orElse(Role.builder().name(ERole.ROLE_MANAGER).build());

        roleRepository.save(roleManager);
        Set<Role> roles = user.getRoles();
        roles.add(roleManager);
        user.setRoles(roles);
        userRepository.save(user);

        Manager manager = Manager.builder()
                .user(user)
                .build();

        log.info("ActionLog.add.end");
        return managerRepository.save(manager);
    }

    @Override
    public List<Employee> getOwnEmployees(Principal principal) {
        log.info("ActionLog.getOwnEmployees.start");
        User managerUser = userRepository.findByUsername(principal.getName()).orElseThrow(() ->
                new UsernameNotFoundException("Username not found with name " + principal.getName()));
        List<Employee> employees = employeeRepository.findAllByManagerId(managerUser.getId());
        log.info("ActionLog.getOwnEmployees.end");
        return employees;
    }
}
