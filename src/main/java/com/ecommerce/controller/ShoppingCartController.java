package com.ecommerce.controller;

import com.ecommerce.model.Invoice;
import com.ecommerce.model.dto.ShoppingCartDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.mapper.ShoppingCartMapper;
import com.ecommerce.model.dto.ShoppingCartProductDTO;
import com.ecommerce.service.StoreService;
import com.ecommerce.utils.InvoicePDFExporter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/shoppingcart")
@RestController
public class ShoppingCartController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @ApiOperation(value = "Add Product to Shopping Cart")
    @PostMapping("/")
    public ResponseEntity<String> addProduct(@RequestBody ShoppingCartProductDTO data) throws Error {
        return new ResponseEntity<>(storeService.addProductToShoppingCart(data), HttpStatus.OK);
    }

    @ApiOperation(value = "Add Product to a determined Shopping Cart by id")
    @PostMapping("/{idShoppingCart}")
    public ResponseEntity<String> addMoreProduct(@PathVariable("idShoppingCart") Long shoppingcart, @RequestBody ShoppingCartProductDTO data) throws Error {
        return new ResponseEntity<>(storeService.addMoreProductstoShopping(shoppingcart, data), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Shopping Cart by id")
    @GetMapping("/{idShoppingCart}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCart(@PathVariable("idShoppingCart") Long idShoppingCart) throws Error {
        return new ResponseEntity<>(shoppingCartMapper.shoppingCartToDTO(storeService.getShoppingCart(idShoppingCart)), HttpStatus.OK);
    }

    @ApiOperation(value = "Remove Product from a determined Shopping Cart by id")
    @PatchMapping("/{idshoppingcart}/{idproduct}")
    public ResponseEntity<String> removeProduct(@PathVariable("idshoppingcart") Long idshoppingcart, @PathVariable("idproduct") Long idproduct) throws Error {
        return new ResponseEntity<>(storeService.deleteProductFromShoppingCart(idshoppingcart, idproduct), HttpStatus.OK);
    }

    @ApiOperation(value = "Checkout Shopping Cart")
    @PostMapping("/{idShoppingCart}/checkout")
    public ResponseEntity<String> checkout(@PathVariable("idShoppingCart") Long idShoppingCart) throws Error, IOException {
        return new ResponseEntity<>(storeService.checkoOut(idShoppingCart), HttpStatus.OK);
    }
    @ApiOperation(value = "Get all Shopping Carts")
    @GetMapping
    public ResponseEntity<List<ShoppingCartDTO>> getAllShoppingCarts() throws Error {
        return new ResponseEntity<>(shoppingCartMapper.shoppingCartListToDTOList(storeService.getAllShoppingCart()), HttpStatus.OK);
    }

}
