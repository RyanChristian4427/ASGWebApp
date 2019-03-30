package com.assessment.asg.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetDto {
    private String emailAddress;
    private String password;
    private String matchingPassword;
}
