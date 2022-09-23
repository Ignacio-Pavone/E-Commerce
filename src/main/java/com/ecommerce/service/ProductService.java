package com.ecommerce.service;


import com.ecommerce.exception.Error;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) throws Error {
        return productRepository.findById(id).orElseThrow(() -> new Error("Product not found"));
    }

    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Product createProduct(Product product) throws Error {
        Product exist = findProductByName(product.getName());
        if (exist != null) {
            throw new Error("Product already exists");
        }
        return productRepository.save(product);
    }

    public boolean setCustomization(Long id_product, Long id_custom) throws Error {
        Product product = findProductById(id_product);
        if (product == null) {
            throw new Error("Product not found");
        }
        product.setCustom_id(id_custom);
        productRepository.save(product);
        return true;
    }
}
