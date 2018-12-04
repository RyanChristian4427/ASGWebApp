package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class VerificationToken {

    private String userEmail;
    private String token;
    private LocalDateTime expiryDate;
}