package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String emailAddress;
    private String password;
    private String role;
//    private boolean enabled;
//    private boolean authenticated;
}
