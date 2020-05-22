package com.kpi.korolova.shop.controllers;

import com.kpi.korolova.shop.entities.ProductName;
import com.kpi.korolova.shop.repository.ProductNameRepository;
import com.kpi.korolova.shop.repository.specifications.ProductSpecification;
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

@Controller
@RequestMapping("/api/shop")
public class ShopController {
    private static final Logger logger = Logger.getLogger(ShopController.class);
    @Autowired
    private ProductNameRepository productNameRepository;

    @Autowired
    private ProductService productService;

    @ResponseBody
    @GetMapping("/products")
    public ModelMap getProductsForShop(@RequestParam(required = false) String name,
                                       @RequestParam(value = "min_price", required = false) BigDecimal minPrice,
                                       @RequestParam(value = "max_price", required = false) BigDecimal maxPrice,
                                       @RequestParam(required = false) String color,
                                       @RequestParam int page,
                                       @RequestParam int size) {
        ModelMap modelMap = new ModelMap();
        try {
            Specification<ProductName> nameSpecification = new ProductSpecification(name, null, null, null);
            Specification<ProductName> minPriceSpecification = new ProductSpecification(name, minPrice, null, null);
            Specification<ProductName> maxPriceSppecification = new ProductSpecification(null, null, maxPrice, null);
            Specification<ProductName> colorSpecification = new ProductSpecification(null, null, null, color);
            Specification<ProductName> specification = Specification
                    .where(nameSpecification)
                    .and(minPriceSpecification)
                    .and(maxPriceSppecification)
                    .and(colorSpecification);
            modelMap.addAttribute("data",
                    productNameRepository.findAll(specification, PageRequest.of(page - 1, size)).getContent());
            modelMap.addAttribute("count", productNameRepository.count(Specification.where(specification)));
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
}