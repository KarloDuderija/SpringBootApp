package com.identyum.project.controllers;

import com.identyum.project.domain.User;
import com.identyum.project.dto.UserDTO;
import com.identyum.project.services.UserService;
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
    private UserService userService;

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
        User userExists = userService.findUserByUserName(user.getUserName());

        if(userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "User with that username already exists");
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        }
        if(!bindingResult.hasErrors()) {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
        }
        modelAndView.setViewName("redirect:/verify");
        return modelAndView;
    }

    @GetMapping("/login")
    public String viewSignIn() {
        return "login";
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
