package com.assessment.drones;

import com.assessment.drones.domain.RegistrationDto;
import com.assessment.drones.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DronesApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRegistration() {
        RegistrationDto accountDto = new RegistrationDto("Ryan", "Christian",
                "ryan@gmail.com", "pass", "pass", "AddressLine1", "AddressLine2",
                "56123", "Cardiff", "262-949-7898", "", "10/15/2000", "Cardiff",
                "None", "Cardiff", "DroneMake", "droneModel");
        assertThat(1 == userRepository.saveUser(accountDto));
    }

}
