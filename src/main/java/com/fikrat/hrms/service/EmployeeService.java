package com.fikrat.hrms.service;

import com.fikrat.hrms.dto.employee.AddEmployeeDto;
import com.fikrat.hrms.dto.employee.EmployeeRequestDto;
import com.fikrat.hrms.model.Employee;

import java.security.Principal;

public interface EmployeeService {
    Employee add(AddEmployeeDto dto);

    Employee add(Principal principal, EmployeeRequestDto employeeDto);
}
