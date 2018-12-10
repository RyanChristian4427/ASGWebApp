package com.assessment.drones.controllers.candidate;

import com.assessment.drones.domain.registration.CourseRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CourseRegistrationController {

    @RequestMapping(path="/courseRegister", method = RequestMethod.POST)
    public ModelAndView registerCourse(@ModelAttribute("user") @Valid CourseRegistrationDto registrationDto,
                                            BindingResult result, WebRequest request) {

        if (!result.hasErrors()) {

            try {

                return new ModelAndView("redirect:/login");
            } catch (Exception e) {
                return new ModelAndView("register","user", registrationDto);
            }
        } else {
            return new ModelAndView("register","user", registrationDto);
        }
    }
}
