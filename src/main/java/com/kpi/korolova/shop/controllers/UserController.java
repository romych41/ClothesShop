package com.kpi.korolova.shop.controllers;

import com.kpi.korolova.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ModelMap getCurrentUser() {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", userService.getCurrentUser());
        return modelMap;
    }
}
