package com.assessment.drones.controllers.candidate;

import com.assessment.drones.domain.Candidate;
import com.assessment.drones.domain.ReviewDto;
import com.assessment.drones.domain.registration.CourseRegistrationDto;
import com.assessment.drones.services.interfaces.CandidateService;
import com.assessment.drones.services.interfaces.ReviewService;
import com.assessment.drones.services.interfaces.StorageService;
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

    private final CandidateService candidateService;
    private final StorageService storageService;
    private final ReviewService reviewService;

    @Autowired
    public ClientDashboardController(CandidateService candidateService, StorageService storageService, ReviewService reviewService){
        this.candidateService = candidateService;
        this.storageService = storageService;
        this.reviewService = reviewService;
    }

    @GetMapping(path = "/dashboard")
    public ModelAndView viewDashboard() {
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
        return new ModelAndView("client-dashboard", model);
    }

    @PostMapping(path = "/updateDetails")
    public void updateClientDetails(@ModelAttribute("updateAddress") CourseRegistrationDto accountDto) {
        candidateService.registerNewCandidate(accountDto);
    }

    @PostMapping(path = "/submitReview")
    public ModelAndView submitReview(@ModelAttribute("review") ReviewDto reviewDto) {
        reviewService.addReview(reviewDto);
        return new ModelAndView("redirect:/dashboard");
    }
}
