package com.assessment.asg;

import com.assessment.asg.models.User;
import com.assessment.asg.models.courseProgress.OperatorsManualDto;
import com.assessment.asg.models.registration.DroneDto;
import com.assessment.asg.models.registration.CourseRegistrationDto;
import com.assessment.asg.models.registration.UserRegistrationDto;
import com.assessment.asg.db.AdminRepository;
import com.assessment.asg.db.CandidateRepository;
import com.assessment.asg.services.AdminService;
import com.assessment.asg.services.CandidateService;
import com.assessment.asg.services.UserService;
import org.flywaydb.core.Flyway;
import org.junit.Before;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AsgApplicationTests {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    Flyway flyway;

    @Before
    public void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void testUserRegistration() {
        User user = userService.registerNewUser(new UserRegistrationDto("Enrico", "Fermi",
                "enrico@gmail.com", "password", "password"));
        assert (!user.isAuthenticated() && user.getEmailAddress().equals("enrico@gmail.com"));
    }

    @Test
    public void testCourseRegistration() {
        CourseRegistrationDto accountDto = new CourseRegistrationDto("admin@asg.com", "ASG-003-18-12", "AddressLine1", "AddressLine2",
                "CF10 4BE", "Cardiff", "262-949-7898", 5, "", LocalDate.of(2018, 1, 1), "10/15/2000", "Cardiff",
                "None", "Cardiff", new DroneDto("DJI", "Matrix"), false);
        assert (1 == candidateRepository.saveUser(accountDto));
    }

    @Test
    public void testOperatorsManual() {
        candidateService.saveOperatorsManual(new OperatorsManualDto("ASG-002-18-11", 0L, "upload-dir/filename"));
        assert (adminRepository.findOperationsManual("ASG-002-18-11").isPresent());
        assert (adminService.verify(new OperatorsManualDto("ASG-002-18-11", 1L, "")));
    }

}
