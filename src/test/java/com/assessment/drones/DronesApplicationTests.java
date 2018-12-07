package com.assessment.drones;

import com.assessment.drones.domain.RegistrationDto;
import com.assessment.drones.repository.interfaces.CandidateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureJdbc
@DirtiesContext
public class DronesApplicationTests {

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    public void testRegistration() {
        RegistrationDto accountDto = new RegistrationDto("Ryan", "Christian",
                "ryan@gmail.com", "pass", "pass", "AddressLine1", "AddressLine2",
                "56123", "Cardiff", "262-949-7898", 5, "", LocalDate.of(2018, 01, 01), "10/15/2000", "Cardiff",
                "None", "Cardiff", "DroneMake", "droneModel");
        assert(1 == candidateRepository.saveUser(accountDto, "ASG-003-18-12"));
    }
}
