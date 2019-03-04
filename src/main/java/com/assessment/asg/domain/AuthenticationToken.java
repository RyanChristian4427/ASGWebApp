package com.assessment.asg.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AuthenticationToken {

    private String userEmail;
    private String token;
    private LocalDateTime expiryDate;
}
