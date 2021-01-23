package com.identyum.project.controllers;

import com.identyum.project.domain.User;
import com.identyum.project.dto.UserDTO;
import com.identyum.project.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AppController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/sign-up")
    public ModelAndView viewSignUp() {
        ModelAndView modelAndView = new ModelAndView();
        UserDTO user = new UserDTO();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @PostMapping("/sign-up")
    public ModelAndView processRegister(@Valid final UserDTO user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userServiceImpl.findUserByUserName(user.getUserName());

        if(userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "User with that username already exists");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        }
        else {
            userServiceImpl.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("verify");
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping( "/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/verify")
    public String viewVerify() {
        return "verify";
    }

    @GetMapping("/submit-otp")
    public String viewSubmitOtp() {
        return "submitotp";
    }

    @GetMapping("/details")
    public String viewDetails() {
        return "details";
    }

    @GetMapping("/my-images")
    public String viewMyImages() {
        return "myimages";
    }

    @GetMapping("/shared-with-me")
    public String viewSharedWithMe() {
        return "sharedwithme";
    }

    @GetMapping("/image/{id}")
    public String viewSingle() {
        return "images";
    }
}
