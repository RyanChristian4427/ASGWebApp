package com.assessment.drones.controllers.candidate;

import com.assessment.drones.domain.registration.CourseRegistrationDto;
import com.assessment.drones.services.interfaces.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private CandidateService candidateService;

    @Autowired
    public CourseRegistrationController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @RequestMapping(path="/courseRegister", method = RequestMethod.POST)
    public ModelAndView registerCourse(@ModelAttribute("courseRegistration") @Valid CourseRegistrationDto registrationDto,
                                            BindingResult result) {

        Map<String, Object> model = new HashMap<>();
        model.put("updateAddress", new CourseRegistrationDto());
        model.put("courseRegistration", registrationDto);

        if (!result.hasErrors()) {
            candidateService.registerNewCandidate(registrationDto);
            return new ModelAndView("redirect:/dashboard", model);
        } else {

            return new ModelAndView("client-dashboard", model);
        }
    }
}
