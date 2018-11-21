package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String emailAddress;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    //More can be added later, depending on what we want to show on
    //the dashboard page
}
