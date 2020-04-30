package com.kpi.korolova.shop.controllers;

import com.kpi.korolova.shop.model.User;
import com.kpi.korolova.shop.repository.UserRepository;
import com.kpi.korolova.shop.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ModelMap login(@RequestBody User user) {
        ModelMap modelMap = new ModelMap();
        userRepository.findByUsername(user.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        modelMap.addAttribute("data", tokenProvider.createToken(authentication));
        return modelMap;
    }
}
