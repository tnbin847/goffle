package com.tnbin.goffle.domain.user.controller;


import com.tnbin.goffle.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/sign-up")
    public ModelAndView signUp() {
        return new ModelAndView("app/user/signup");
    }
}