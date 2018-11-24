package com.assessment.drones.controllers;

import com.assessment.drones.domain.UserDto;
import com.assessment.drones.services.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        UserDto accountDto = new UserDto();
        model.addAttribute("user", accountDto);
        return "register";
    }

    public String register(Model model, UserDto accountDto){
        model.addAttribute("user", accountDto);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result,
            Model model) {

        if (!result.hasErrors()) {
            registerUserService.registerNewUserAccount(accountDto);
        }
        if (result.hasErrors()) {
            return register(model, accountDto);
        } else {
            return "redirect:/login";
        }
    }
}
