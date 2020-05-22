package com.kpi.korolova.shop.service;

import com.kpi.korolova.shop.exceptions.InvalidParamsException;
import com.kpi.korolova.shop.model.Size;
import com.kpi.korolova.shop.model.CsvData;
import com.kpi.korolova.shop.model.CsvProduct;
import com.kpi.korolova.shop.entities.ProductModel;
import com.kpi.korolova.shop.entities.ProductName;
import com.kpi.korolova.shop.repository.ProductNameRepository;
import com.kpi.korolova.shop.repository.ProductRepository;
import com.kpi.korolova.shop.util.DbUtil;
import com.kpi.korolova.shop.util.GenericPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductNameRepository productNameRepository;

    public void importProducts(CsvData csvData) {
        List<ProductName> productModels = new ArrayList<>();
        for (CsvProduct csvProduct : csvData.getCsvProducts()) {
            ProductName productName = new ProductName();
            StringBuilder sb = new StringBuilder();
            String[] strings = csvProduct.getName().split(" ");
            for (int i = 0; i < strings.length - 1; i++) {
                sb.append(strings[i]);
                if (i < strings.length - 1) {
                    sb.append(" ");
                }
            }
            productName.setName(sb.toString());
            productName.setColor(csvProduct.getColor());
            if (productNameRepository.existsByNameAndColor(productName.getName(), productName.getColor())) {
                productName = productNameRepository.getByNameAndColor(productName.getName(), productName.getColor());
            } else {
                productName = productNameRepository.save(productName);
            }
            ProductModel product;
            if (productRepository.existsByProductNameAndSize(productName, Size.valueOf(csvProduct.getSize()))) {
                product = productRepository.findByProductNameAndSize(productName, Size.valueOf(csvProduct.getSize()));
            } else {
                product = new ProductModel();
                productName.getProductModels().add(product);
                product.setProductName(productName);
                product.setSize(Size.valueOf(csvProduct.getSize()));
            }
            product.setPrice(csvProduct.getPrice());
            productModels.add(productName);
        }
        for (ProductName productModel : productModels) {
            productRepository.saveAll(productModel.getProductModels());
        }
        productNameRepository.saveAll(productModels);
    }

    public List<ProductName> getAllProducts(Pageable pageable) {
        return productNameRepository.findAll(pageable).getContent();
    }

    public long getAllProductsCount() {
        return productRepository.count();
    }

    public ProductName getProduct(int id) throws InvalidParamsException {
        if (id < 1) {
            throw new InvalidParamsException(String.format("id: %s", id));
        }
        return productNameRepository.findById(id).get();
    }

    public void editProductName(ProductName product) {
        ProductName old = productNameRepository.getOne(product.getId());
        product.setPhFormat(old.getPhFormat());
        product.setPhoto(old.getPhoto());
        productNameRepository.save(product);
    }

    public void editProductModel(ProductModel product) {
        ProductModel old = productRepository.findById(product.getId()).get();
        old.setSize(product.getSize());
        old.setPrice(product.getPrice());
        old.setAttributes(product.getAttributes());
        productRepository.save(old);
    }

    public GenericPair<String, String> getProductPhoto(int productNameId) {
        ProductName productName = productNameRepository.getOne(productNameId);
        return new GenericPair<>(productName.getPhoto(), productName.getPhFormat());
    }

    public void setProductPhoto(int productNameId, MultipartFile photo) throws IOException {
        ProductName productName = productNameRepository.getOne(productNameId);
        String imageEncoded = null;
        String format = null;
        if (photo != null) {
            imageEncoded = DbUtil.encodeImage(photo);
            format = photo.getContentType().split("/")[1];
        }
        productName.setPhoto(imageEncoded);
        productName.setPhFormat(format);
        productNameRepository.save(productName);
    }
}
