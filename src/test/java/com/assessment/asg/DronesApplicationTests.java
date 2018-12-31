package com.assessment.asg;

import com.assessment.asg.domain.Candidate;
import com.assessment.asg.domain.registration.DroneDto;
import com.assessment.asg.domain.registration.CourseRegistrationDto;
import com.assessment.asg.repository.interfaces.CandidateRepository;
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
        CourseRegistrationDto accountDto = new CourseRegistrationDto("admin@asg.com", "ASG-003-18-12", "AddressLine1", "AddressLine2",
                "CF10 4BE", "Cardiff", "262-949-7898", 5, "", LocalDate.of(2018, 01, 01), "10/15/2000", "Cardiff",
                "None", "Cardiff", new DroneDto("DJI", "Matrix"), false);
        assert (1 == candidateRepository.saveUser(accountDto));
    }
}
