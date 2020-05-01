package com.kpi.korolova.shop.controllers;

import com.kpi.korolova.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ModelMap getCurrentUser() {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("data", userService.getCurrentUser());
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }
}
