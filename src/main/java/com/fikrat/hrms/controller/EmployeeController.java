package com.fikrat.hrms.controller;

import com.fikrat.hrms.dto.employee.AddEmployeeDto;
import com.fikrat.hrms.dto.employee.EmployeeRequestDto;
import com.fikrat.hrms.model.Employee;
import com.fikrat.hrms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> add(@RequestBody AddEmployeeDto dto){
        return ResponseEntity.ok(employeeService.add(dto));
    }

    @PostMapping("/byManager")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Employee> add(Principal principal, @RequestBody EmployeeRequestDto employeeRequestDto){
        return ResponseEntity.ok(employeeService.add(principal,employeeRequestDto));
    }
}
