package com.ecommerce.controller;


import com.ecommerce.dto.UserDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws Error {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) throws Error {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.FOUND);
    }

    @PatchMapping("/setCustomization/{id_producto}")
    public ResponseEntity<String> setCustomization(@PathVariable("id_producto") Long id_producto, @RequestBody Product product) throws Error {
        if (productService.setCustomization(id_producto, product.getCustom_id())) {
            return new ResponseEntity<>("Customization set", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

}
