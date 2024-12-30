package com.tnbin.goffle.domain.user.controller;

import com.tnbin.goffle.domain.user.dto.SignUpRequest;
import com.tnbin.goffle.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/sign-up")
    public ModelAndView signup() {
        return new ModelAndView("app/user/signup");
    }

    @PostMapping("/api/v1/user/exists/login-id/{loginId}")
    public ResponseEntity<Boolean> findExistsByLoginId(@PathVariable("loginId") final String loginId) {
        boolean isPresent = userService.existsUserByLoginId(loginId);
        return ResponseEntity.ok().body(isPresent);
    }

    @PostMapping("/api/v1/user/exists/email/{email}")
    public ResponseEntity<Boolean> findExistsByEmail(@PathVariable("email") final String email) {
        boolean isPresent = userService.existsUserByEmail(email);
        return ResponseEntity.ok().body(isPresent);
    }

    @PostMapping("/api/v1/user")
    public ResponseEntity<Void> createUser(@RequestBody @Valid final SignUpRequest request) {
        userService.addUser(request);
        return ResponseEntity.ok().build();
    }
}