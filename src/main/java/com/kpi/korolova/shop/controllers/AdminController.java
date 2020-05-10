package com.kpi.korolova.shop.controllers;

import com.kpi.korolova.shop.entities.Product;
import com.kpi.korolova.shop.model.CsvData;
import com.kpi.korolova.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @GetMapping("/products")
    public ModelMap getAllProducts(@RequestParam int page,
                                   @RequestParam int size,
                                   @RequestParam String sort) {
        ModelMap modelMap = new ModelMap();
        try {
            Pageable pageable = PageRequest.of(page-1, size, Sort.by(sort));
            modelMap.addAttribute("data", productService.getAllProducts(pageable));
            modelMap.addAttribute("count", productService.getAllProductsCount());
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @PutMapping("/product")
    public ModelMap editProduct(@RequestBody Product product) {
        ModelMap modelMap = new ModelMap();
        try {
            productService.editProduct(product);
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }
}
