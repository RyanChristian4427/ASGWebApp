package com.assessment.drones.controllers.candidate;

import com.assessment.drones.domain.registration.CourseRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CourseRegistrationController {

    @RequestMapping(path="/courseRegister", method = RequestMethod.POST)
    public ModelAndView registerCourse(@ModelAttribute("courseRegistration") @Valid CourseRegistrationDto registrationDto,
                                            BindingResult result) {

        Map<String, Object> model = new HashMap<>();
        model.put("updateAddress", new CourseRegistrationDto());
        model.put("courseRegistration", registrationDto);

        if (!result.hasErrors()) {
            System.out.println("No errors in form post");
            return new ModelAndView("client-dashboard", model);
        } else {
            System.out.println("Catches issue: " + registrationDto.getPostCode() + " " + registrationDto.getEnglishLevel());
            return new ModelAndView("client-dashboard", model);
        }
    }
}
