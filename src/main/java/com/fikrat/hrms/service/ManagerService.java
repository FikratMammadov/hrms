package com.fikrat.hrms.service;

import com.fikrat.hrms.dto.employee.EmployeeResponseDto;
import com.fikrat.hrms.dto.manager.ManagerDto;
import com.fikrat.hrms.model.Employee;
import com.fikrat.hrms.model.Manager;

import java.security.Principal;
import java.util.List;

public interface ManagerService {
    Manager add(ManagerDto managerDto);
    List<Employee> getOwnEmployees(Principal principal);
}
