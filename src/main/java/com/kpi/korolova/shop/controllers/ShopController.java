package com.kpi.korolova.shop.controllers;

import com.kpi.korolova.shop.entities.Order;
import com.kpi.korolova.shop.entities.ProductName;
import com.kpi.korolova.shop.model.Category;
import com.kpi.korolova.shop.repository.ProductNameRepository;
import com.kpi.korolova.shop.repository.specifications.ProductSpecification;
import com.kpi.korolova.shop.service.OrderService;
import com.kpi.korolova.shop.service.ProductService;
import com.kpi.korolova.shop.util.DbUtil;
import com.kpi.korolova.shop.util.GenericPair;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/shop")
public class ShopController {
    private static final Logger logger = Logger.getLogger(ShopController.class);
    @Autowired
    private ProductNameRepository productNameRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @ResponseBody
    @GetMapping("/products")
    public ModelMap getProductsForShop(@RequestParam(required = false) String name,
                                       @RequestParam(value = "min_price", required = false) BigDecimal minPrice,
                                       @RequestParam(value = "max_price", required = false) BigDecimal maxPrice,
                                       @RequestParam(required = false) String color,
                                       @RequestParam(required = false) List<String> category,
                                       @RequestParam int page,
                                       @RequestParam int size) {
        ModelMap modelMap = new ModelMap();
        try {
            List<Category> categories = new ArrayList<>();
            if(category != null && !category.isEmpty()) {
                for (String c : category) {
                    categories.add(Category.fromDescription(c));
                }
            }
            Specification<ProductName> specification = new ProductSpecification(name, minPrice, maxPrice, color, categories);
            modelMap.addAttribute("data",
                    productNameRepository.findAll(specification, PageRequest.of(page, size)).getContent());
            modelMap.addAttribute("count", productNameRepository.count(Specification.where(specification)));
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @ResponseBody
    @GetMapping("/products/categories")
    public ModelMap getProductCategories() {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("data", productService.getAllCategories());
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @GetMapping("/products/image/{id}")
    public void getImageForProduct(HttpServletResponse response, @PathVariable("id") int productId) {
        response.setCharacterEncoding("UTF-8");
        try {
            GenericPair<String, String> imageEncoded = productService.getProductPhoto(productId);
            response.setContentType("image/" + imageEncoded.getP2());
            response.getOutputStream().write(DbUtil.decodeImage(imageEncoded.getP1()));
            response.getOutputStream().close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @ResponseBody
    @PostMapping("/order")
    public ModelMap addOrder(@RequestBody Order order) {
        ModelMap modelMap = new ModelMap();
        try {
            orderService.addOrder(order, true);
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @ResponseBody
    @GetMapping("/order/delivery")
    public ModelMap getDeliveryMethods() {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }
}
