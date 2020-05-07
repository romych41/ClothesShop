package com.kpi.korolova.shop.service;

import com.kpi.korolova.shop.ProductDao;
import com.kpi.korolova.shop.entities.Product;
import com.kpi.korolova.shop.model.CsvData;
import com.kpi.korolova.shop.model.CsvProduct;
import com.kpi.korolova.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDao productDao;

    public void importProducts(CsvData csvData) {
        List<Product> productList = new ArrayList<>();
        for(CsvProduct csvProduct : csvData.getCsvProducts()) {
            productList.add(csvProduct.toProduct());
        }
        productDao.upsertProducts(productList);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public long getAllProductsCount() {
        return productRepository.count();
    }
}
