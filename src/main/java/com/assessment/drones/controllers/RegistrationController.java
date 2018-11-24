package com.assessment.drones.controllers;

import com.assessment.drones.controllers.exceptions.EmailExistsException;
import com.assessment.drones.domain.UserDto;
import com.assessment.drones.domain.User;
import com.assessment.drones.services.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private RegisterUserService registerUserService;

    @Autowired
    public RegistrationController(RegisterUserService aService) {
        registerUserService = aService;
    }

    @RequestMapping(path="/register", method= RequestMethod.GET)
    public String register(Model model){
        UserDto userDTO = new UserDto();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result) {

        if (!result.hasErrors()) {
            registerUserService.registerNewUserAccount(accountDto);
        }
        if (result.hasErrors()) {
            return new ModelAndView("register", "user", accountDto);
        } else {
            return new ModelAndView("login", "user", accountDto);
        }
    }
}
