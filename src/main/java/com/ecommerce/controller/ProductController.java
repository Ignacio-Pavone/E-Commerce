package com.ecommerce.controller;


import com.ecommerce.model.BaseCustomization;
import com.ecommerce.model.dto.ShowSellProductDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.model.Product;
import com.ecommerce.model.SellProduct;
import com.ecommerce.repository.SellProductRepository;
import com.ecommerce.service.CustomizationService;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private SellProductRepository sellProductRepository;
    @Autowired
    private CustomizationService customizationService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws Error {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) throws Error {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) throws Error {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.FOUND);
    }

    @GetMapping("/allsell")
    public ResponseEntity<List<ShowSellProductDTO>> getAllSell() {
        return new ResponseEntity<>(productService.findAllsellProducts(), HttpStatus.OK);
    }


    @GetMapping("/sellproducts")
    public ResponseEntity<List<SellProduct>> getSellProducts() {
        return new ResponseEntity<>(sellProductRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("/filters")
    //filters?order=DESC/ASC&name=nombre
    //filters?name=nombre
    public ResponseEntity<List<ShowSellProductDTO>> getProductsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "ASC") String order) {
        return new ResponseEntity<>(productService.getByFilters(name, order), HttpStatus.OK);
    }

    @PostMapping("/customizations/base")
    public ResponseEntity<BaseCustomization> createCustomization(@RequestBody BaseCustomization bCustomizationController) {
        return ResponseEntity.ok(customizationService.createCustomization(bCustomizationController));
    }

    @GetMapping("/customizations/base")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(customizationService.findAll());
    }

    @DeleteMapping("/customizations/base/{id}")
    public ResponseEntity<?> deleteCustomization(@PathVariable Long id) {
        customizationService.deleteCustomization(id);
        return ResponseEntity.ok("Customization deleted");
    }

    @PatchMapping("/customizations/setBaseCustomization/{id_producto}")
    public ResponseEntity<String> setCustomization(@PathVariable("id_producto") Long id_producto, @RequestBody Product product) throws Error {
        if (productService.setCustomization(id_producto, product.getCustom_id())) {
            return new ResponseEntity<>("Customization set", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }


}
