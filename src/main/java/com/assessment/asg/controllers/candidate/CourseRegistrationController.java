package com.assessment.asg.controllers.candidate;

import com.assessment.asg.domain.ReviewDto;
import com.assessment.asg.domain.registration.CourseRegistrationDto;
import com.assessment.asg.services.interfaces.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CourseRegistrationController {

    private CandidateService candidateService;

    @Autowired
    public CourseRegistrationController(final CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping(path = "/courseRegister")
    public ModelAndView registerCourse(final @ModelAttribute("courseRegistration") @Valid CourseRegistrationDto registrationDto,
                                       final BindingResult result) {

        Map<String, Object> model = new HashMap<>();
        model.put("updateAddress", new CourseRegistrationDto());
        model.put("courseRegistration", registrationDto);
        model.put("review", new ReviewDto());


        if (!result.hasErrors()) {
            candidateService.registerNewCandidate(registrationDto);
            return new ModelAndView("redirect:/dashboard", model);
        } else {
            model.put("userRegistered", false);
            return new ModelAndView("dashboard/candidate/index", model);
        }
    }
}
