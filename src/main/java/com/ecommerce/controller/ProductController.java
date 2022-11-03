package com.ecommerce.controller;


import com.ecommerce.model.BaseCustomization;
import com.ecommerce.model.dto.CreateProductDTO;
import com.ecommerce.model.dto.ShowSellProductDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.model.Product;
import com.ecommerce.model.SellProduct;
import com.ecommerce.repository.SellProductRepository;
import com.ecommerce.service.CustomizationService;
import com.ecommerce.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Get all Products")
    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a Product")
    @PostMapping()
    public ResponseEntity<CreateProductDTO> createProduct(@RequestBody CreateProductDTO product) throws Error {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Delete Product")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) throws Error {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
    @ApiOperation(value = "Get Product by id")
    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) throws Error {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.FOUND);
    }
    @ApiOperation(value = "Get all Sellers Products")
    @GetMapping("/allsell")
    public ResponseEntity<List<ShowSellProductDTO>> getAllSell() {
        return new ResponseEntity<>(productService.findAllsellProducts(), HttpStatus.OK);
    }
    @ApiOperation(value = "Get Products Sell by the Plataform")
    @GetMapping("/sellproducts")
    public ResponseEntity<List<SellProduct>> getSellProducts() {
        return new ResponseEntity<>(sellProductRepository.findAll(), HttpStatus.OK);
    }
    @ApiOperation(value = "Search products with Filter")
    @GetMapping("/filters")
    //filters?order=DESC/ASC&name=malla
    //filters?name=nombre
    public ResponseEntity<List<ShowSellProductDTO>> getProductsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "ASC") String order) {
        return new ResponseEntity<>(productService.getByFilters(name, order), HttpStatus.OK);
    }

    @ApiOperation(value = "Add Base Customization")
    @PostMapping("/customizations/")
    public ResponseEntity<BaseCustomization> createCustomization(@RequestBody BaseCustomization bCustomizationController) {
        return ResponseEntity.ok(customizationService.createCustomization(bCustomizationController));
    }

    @ApiOperation(value = "Get all Base Customization")
    @GetMapping("/customizations/")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(customizationService.findAll());
    }
    @ApiOperation(value = "Delete Base Customization")
    @DeleteMapping("/customizations/{id}")
    public ResponseEntity<?> deleteCustomization(@PathVariable Long id) {
        if (customizationService.deleteCustomization(id)) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @ApiOperation(value = "Set Base Customization to Product")
    @PatchMapping("/customizations/{id_producto}")
    public ResponseEntity<String> setCustomization(@PathVariable("id_producto") Long id_producto, @RequestBody Product product) throws Error {
        if (productService.setCustomization(id_producto, product.getCustom_id())) {
            return new ResponseEntity<>("Customization set", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customization not set", HttpStatus.NOT_FOUND);
        }
    }
}
