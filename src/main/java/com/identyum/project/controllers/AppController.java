package com.identyum.project.controllers;

import com.identyum.project.domain.User;
import com.identyum.project.dto.CodeDTO;
import com.identyum.project.dto.PhoneDTO;
import com.identyum.project.dto.UserDTO;
import com.identyum.project.services.UserServiceImpl;
import com.identyum.project.verify.TwoFactorVerify;
import com.nexmo.client.verify.VerifyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AppController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TwoFactorVerify twoFactorVerify;


    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/signup")
    public ModelAndView viewSignUp() {
        ModelAndView modelAndView = new ModelAndView();
        UserDTO user = new UserDTO();
        modelAndView.addObject("userDTO", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @PostMapping("/signup")
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
            System.out.println("Managed to enter else with user" + user);
            userServiceImpl.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("success");
        }
        return modelAndView;
    }

    @GetMapping( "/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/verify")
    public ModelAndView viewVerify(Principal principal, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userServiceImpl.findUserByUserName(principal.getName());
        System.out.println("user ++>>" + userExists);
        String existsPhone = userExists.getPhone();
        if(existsPhone != null) {
            session.setAttribute("user", userExists);
            modelAndView.setViewName("redirect:/details");
        } else {
            PhoneDTO phone = new PhoneDTO();
            modelAndView.addObject("phoneDTO", phone);
            modelAndView.setViewName("verify");
        }
        return modelAndView;
    }

    @PostMapping("/phone_register")
    public ModelAndView processValidation(final PhoneDTO phoneDTO, Principal principal) {
        String requestId = "9e1c90d4b3734e81a3fac2dacceb4660";
        ModelAndView modelAndView = new ModelAndView();

        User user = userServiceImpl.findUserByUserName(principal.getName());
        VerifyRequest request = new VerifyRequest( "+385" + phoneDTO.getNumber(), "Identyum");
        request.setLength(4);

//        CheckResponse response = twoFactorVerify.nexmoVerifyClient(client).check(requestId, "1591");
//        if (response.getStatus() == VerifyStatus.OK) {
//            System.out.printf("Complete - price: %s", response.getPrice());
//        } else {
//            System.out.printf("ERROR! %s: %s",
//                    response.getStatus(),
//                    response.getErrorText()
//            );
//        }
        System.out.println("user that wants to register:"+user);
        System.out.println("number is:"+phoneDTO.getNumber());
        modelAndView.setViewName("redirect:/submitotp");
        return modelAndView;
    }

    @GetMapping("/submitotp")
    public ModelAndView viewSubmitOtp() {
        ModelAndView modelAndView = new ModelAndView();
        CodeDTO code = new CodeDTO();
        modelAndView.addObject("codeDTO", code);
        return modelAndView;
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
