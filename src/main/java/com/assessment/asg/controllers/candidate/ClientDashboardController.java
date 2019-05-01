package com.assessment.asg.controllers.candidate;

import com.assessment.asg.models.Candidate;
import com.assessment.asg.models.ReviewDto;
import com.assessment.asg.models.registration.CourseRegistrationDto;
import com.assessment.asg.services.CandidateService;
import com.assessment.asg.services.ReviewService;
import com.assessment.asg.services.StorageService;
import com.assessment.asg.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ClientDashboardController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final CandidateService candidateService;
    private final StorageService storageService;
    private final ReviewService reviewService;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public ClientDashboardController(final CandidateService candidateService, final StorageService storageService,
                                     final ReviewService reviewService, final UserDetailsServiceImpl userDetailsService) {
        this.candidateService = candidateService;
        this.storageService = storageService;
        this.reviewService = reviewService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(path = "/dashboard")
    public ModelAndView viewDashboard() {
        LOGGER.info("User " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has requested the dashboard");
        Optional<Candidate> candidate = candidateService.findCandidateByCurrentUser();

        Map<String, Object> model = new HashMap<>();
        model.put("updateAddress", new CourseRegistrationDto());
        model.put("courseRegistration", new CourseRegistrationDto());
        model.put("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(OpsManualUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
        model.put("review", new ReviewDto());

        if (candidate.isPresent()) {
            model.put("userRegistered", true);
        } else {
            model.put("userRegistered", false);
        }
        return new ModelAndView("dashboard/candidate/index", model);
    }

    @PostMapping(path = "/updateDetails")
    public void updateClientDetails(final @ModelAttribute("updateAddress") CourseRegistrationDto accountDto) {
        LOGGER.info("User " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has submitted new contact information.");
        candidateService.registerNewCandidate(accountDto);
    }

    @PostMapping(path = "/submitReview")
    public ModelAndView submitReview(final @ModelAttribute("review") ReviewDto reviewDto) {
        LOGGER.info("User " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has submitted a course review.");
        reviewService.addReview(reviewDto);
        return new ModelAndView("redirect:/dashboard");
    }
}
