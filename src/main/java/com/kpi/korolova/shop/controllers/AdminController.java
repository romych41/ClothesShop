package com.kpi.korolova.shop.controllers;

import com.kpi.korolova.shop.entities.Order;
import com.kpi.korolova.shop.model.CsvData;
import com.kpi.korolova.shop.entities.ProductModel;
import com.kpi.korolova.shop.entities.ProductName;
import com.kpi.korolova.shop.model.OrderStatus;
import com.kpi.korolova.shop.model.Size;
import com.kpi.korolova.shop.service.OrderService;
import com.kpi.korolova.shop.service.ProductService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private static final Logger logger = Logger.getLogger(ShopController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

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
                                   @RequestParam(required = false) String sort) {
        ModelMap modelMap = new ModelMap();
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
            modelMap.addAttribute("data", productService.getAllProducts(pageable));
            modelMap.addAttribute("count", productService.getAllProductNamesCount());
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @GetMapping("/productName/{id}")
    public ModelMap getProduct(@PathVariable int id) {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("data", productService.getProduct(id));
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @PutMapping("/productName")
    public ModelMap editProduct(@RequestBody ProductName product) {
        ModelMap modelMap = new ModelMap();
        try {
            productService.editProductName(product);
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @PutMapping("/productModel")
    public ModelMap editProduct(@RequestBody ProductModel product) {
        ModelMap modelMap = new ModelMap();
        try {
            productService.editProductModel(product);
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @PostMapping("/products/image")
    public ModelMap setImageForProduct(@RequestParam("product_id") int productId,
                                       @RequestParam MultipartFile photo) {
        ModelMap modelMap = new ModelMap();
        try{
            productService.setProductPhoto(productId, photo);
            modelMap.addAttribute("success", true);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @PutMapping("/order")
    public ModelMap editOrder(@RequestBody Order order) {
        ModelMap modelMap = new ModelMap();
        try {
            orderService.editOrder(order);
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @GetMapping("/order")
    public ModelMap getOrders(@RequestParam OrderStatus status,
                              @RequestParam String sort,
                              @RequestParam boolean desc,
                              @RequestParam int page,
                              @RequestParam int size) {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("data", orderService.getAllOrdersByStatus(status, sort, desc, page, size));
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @GetMapping("/order/statuses")
    public ModelMap getOrders() {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("data", OrderStatus.values());
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @GetMapping("/order/byNumber")
    public ModelMap getOrders(@RequestParam Integer number) {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("data", orderService.getOrderByOrderNumber(number));
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @GetMapping("/product/sizes")
    public ModelMap getSizes() {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("data", Size.values());
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @PutMapping("/product/deleteName")
    public ModelMap deleteProductName(@RequestBody ProductName productName) {
        ModelMap modelMap = new ModelMap();
        try {
            productService.deleteProductName(productName.getId(), productName.isDeleted());
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @PutMapping("/product/deleteModel")
    public ModelMap deleteProductModel(@RequestBody ProductModel productModel) {
        ModelMap modelMap = new ModelMap();
        try {
            productService.deleteProductModel(productModel.getId(), productModel.isDeleted());
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }

    @GetMapping("/order/delivery")
    public ModelMap getDeliveryMethods() {
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.addAttribute("data", orderService.getDeliveries(false));
            modelMap.addAttribute("success", true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelMap.addAttribute("success", false);
            modelMap.addAttribute("error", e.getMessage());
        }
        return modelMap;
    }
}
