package com.assessment.drones;

import com.assessment.drones.domain.registration.DroneDto;
import com.assessment.drones.domain.registration.CourseRegistrationDto;
import com.assessment.drones.repository.interfaces.CandidateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureJdbc
@DirtiesContext
public class DronesApplicationTests {

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    public void testRegistration() {
        CourseRegistrationDto accountDto = new CourseRegistrationDto("Ryan", "Christian",
                "ryan@gmail.com", "pass", "pass", "AddressLine1", "AddressLine2",
                "56123", "Cardiff", "262-949-7898", 5, "", LocalDate.of(2018, 01, 01), "10/15/2000", "Cardiff",
                "None", "Cardiff", new DroneDto("DJI", "Matrix"));
        assert(1 == candidateRepository.saveUser(accountDto, "ASG-003-18-12"));
    }
}
