package com.kpi.korolova.shop.controllers;

import com.kpi.korolova.shop.model.CsvData;
import com.kpi.korolova.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private ProductService productService;

    @PostMapping("/importProducts")
    public ModelMap importProducts(@RequestBody CsvData csvData) {
        ModelMap modelMap = new ModelMap();
        try {
            productService.importProducts(csvData);
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
        }
        return modelMap;
    }

    @GetMapping("/products")
    public ModelMap getAllProducts() {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("data", productService.getAllProducts());
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
        }
        return modelMap;
    }
}
