package com.fikrat.hrms.dto.employee;

import com.fikrat.hrms.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
    private Long id;
    private Long userId;
    private Long managerId;
}
