package com.dartacademy.blogkuapp.controller;

import com.dartacademy.blogkuapp.entity.Role;
import com.dartacademy.blogkuapp.entity.User;
import com.dartacademy.blogkuapp.payload.ApiResponse;
import com.dartacademy.blogkuapp.payload.JWTAuthResponse;
import com.dartacademy.blogkuapp.payload.LoginDto;
import com.dartacademy.blogkuapp.payload.SignUpDto;
import com.dartacademy.blogkuapp.repository.RoleRepository;
import com.dartacademy.blogkuapp.repository.UserRepository;
import com.dartacademy.blogkuapp.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get token from token provider
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody SignUpDto signUpDto){
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity(new ApiResponse("Username is already taken!",true),HttpStatus.BAD_GATEWAY);
        }
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity(new ApiResponse("Email is already taken!",true),HttpStatus.BAD_GATEWAY);
        }
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        return new ResponseEntity<>(new ApiResponse("User registered successfully",true),HttpStatus.OK);
    }
}
