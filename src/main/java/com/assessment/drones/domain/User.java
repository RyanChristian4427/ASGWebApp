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
    //More can be added later, depending on what we want to show on
    //the dashboard page
}
