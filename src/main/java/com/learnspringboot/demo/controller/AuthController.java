package com.learnspringboot.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.learnspringboot.demo.dto.JwtResponse;
import com.learnspringboot.demo.dto.MessageResponse;
import com.learnspringboot.demo.dto.SignupDTO;
import com.learnspringboot.demo.dto.UserLoginDTO;
import com.learnspringboot.demo.entity.User;
import com.learnspringboot.demo.security.JwtUtil;
import com.learnspringboot.demo.security.UserPrincipal;
import com.learnspringboot.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Validated @RequestBody UserLoginDTO userLogin) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateJwtToken(authentication);

        UserPrincipal userDetail = (UserPrincipal)authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetail.getId(), userDetail.getUsername(), userDetail.getEmail()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupDTO signup) {
        User user = new User();
        user.setUsername(signup.getUsername());
        user.setEmail(signup.getEmail());
        user.setPassword(signup.getPassword());
        user.setActive(true);

        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User Register successfully!"));
    }
}