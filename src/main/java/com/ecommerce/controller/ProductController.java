package com.ecommerce.controller;


import com.ecommerce.dto.ShowSellProductDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.model.Product;
import com.ecommerce.model.SellProduct;
import com.ecommerce.exception.repository.SellProductRepository;
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

    @PatchMapping("/setCustomization/{id_producto}")
    public ResponseEntity<String> setCustomization(@PathVariable("id_producto") Long id_producto, @RequestBody Product product) throws Error {
        if (productService.setCustomization(id_producto, product.getCustom_id())) {
            return new ResponseEntity<>("Customization set", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }


}
