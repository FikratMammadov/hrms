package com.fikrat.hrms.dto.auth;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class RegisterRequest {
    @NotBlank(message = "firstname is required")
    private String firstName;

    @NotBlank(message = "lastname is required")
    private String lastName;

    @NotNull(message = "city is required")
    private Long cityId;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(max = 20, message = "Password can not be greater than 20")
    private String password;
}
