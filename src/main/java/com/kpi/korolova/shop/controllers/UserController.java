package com.kpi.korolova.shop.controllers;

import com.kpi.korolova.shop.entities.Customer;
import com.kpi.korolova.shop.service.CustomerService;
import com.kpi.korolova.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private CustomerService customerService;
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

    @GetMapping("/customer")
    public ModelMap getCustomerInfo(@RequestParam(value = "customer_id", required = false) Integer customerId) {
        ModelMap modelMap = new ModelMap();
        try {
            Customer result;
            if(customerId == null) {
                result = customerService.getCurrentCustomer();
            } else {
                result = customerService.getCustomer(customerId);
            }
            result.getUser().setPassword("");
            modelMap.addAttribute("data", result);
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @PutMapping("/customer")
    public ModelMap editCustomerInfo(@RequestBody Customer customer) {
        ModelMap modelMap = new ModelMap();
        try {
            customerService.editCustomer(customer);
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }
}
