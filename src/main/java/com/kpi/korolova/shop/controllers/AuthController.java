package com.kpi.korolova.shop.controllers;

import com.kpi.korolova.shop.entities.Customer;
import com.kpi.korolova.shop.entities.User;
import com.kpi.korolova.shop.repository.UserRepository;
import com.kpi.korolova.shop.security.JwtTokenProvider;
import com.kpi.korolova.shop.service.CustomerService;
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

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public ModelMap login(@RequestBody User user) {
        ModelMap modelMap = new ModelMap();
        try {
            userRepository.findByEmail(user.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            modelMap.addAttribute("data", tokenProvider.createToken(authentication));
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @PostMapping("/registration")
    public ModelMap addCustomer(@RequestBody Customer customer) {
        ModelMap modelMap = new ModelMap();
        try {
            customerService.addCustomer(customer);
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }
}
