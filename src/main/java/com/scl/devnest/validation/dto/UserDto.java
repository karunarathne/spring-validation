package com.scl.devnest.validation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.scl.devnest.validation.validation.ValidRoleType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String id;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Pattern(regexp = "^\\+?[0-9\\s.-]+$", message = "Invalid phone number")
    private String phoneNumber;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email address")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+{}\\[\\]:;<>,.?~\\\\/-]).{8,}$", message = "Invalid password. Password should have at least one uppercase letter, one lowercase letter and one digit. Also the minimum password length is 8")
    private String password;

    @Min(value = 18, message = "Age should be greater than 18")
    @Max(value = 60, message = "Age should be less than 60")
    private Integer age;

    @Past(message = "The Enrolled Date should be a past date")
    private Date enrolledDate;

    @ValidRoleType
    private String role;
}
