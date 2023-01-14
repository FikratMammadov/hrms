package com.fikrat.hrms.controller;

import com.fikrat.hrms.dto.manager.ManagerDto;
import com.fikrat.hrms.model.Employee;
import com.fikrat.hrms.model.Manager;
import com.fikrat.hrms.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Manager> add(@RequestBody ManagerDto managerDto) {
        return ResponseEntity.ok(managerService.add(managerDto));
    }

    @GetMapping("/employees")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Employee>> getOwnEmployees(Principal principal) {
        return ResponseEntity.ok(managerService.getOwnEmployees(principal));
    }
}
