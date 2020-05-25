package com.kpi.korolova.shop.controllers;

import com.kpi.korolova.shop.entities.Customer;
import com.kpi.korolova.shop.entities.User;
import com.kpi.korolova.shop.service.CustomerService;
import com.kpi.korolova.shop.service.OrderService;
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
    @Autowired
    private OrderService orderService;

    @GetMapping("/me")
    public ModelMap getCurrentUser() {
        ModelMap modelMap = new ModelMap();
        try {
            User user = userService.getCurrentUser();
            user.setPassword("");
            modelMap.addAttribute("data", user);
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

    @ResponseBody
    @GetMapping("/orders")
    public ModelMap getOrdersPerCustomer() {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("data", orderService.getOrdersByCustomerId(
                    customerService.getCurrentCustomer().getId()));
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }
}
