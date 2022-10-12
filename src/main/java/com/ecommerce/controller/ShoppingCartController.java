package com.ecommerce.controller;

import com.ecommerce.model.Invoice;
import com.ecommerce.model.dto.ShoppingCartDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.mapper.ShoppingCartMapper;
import com.ecommerce.service.StoreService;
import com.ecommerce.utils.InvoicePDFExporter;
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

    @PostMapping("/addproduct/{idstore}/shoppingcart/{idproduct}/{quantity}")
    public ResponseEntity<String> addProduct(@PathVariable("idstore") Long idstore, @PathVariable("idproduct") Long idproduct, @PathVariable("quantity") Integer quantity) throws Error {
        return new ResponseEntity<>(storeService.addProductToShoppingCart(idstore, idproduct, quantity), HttpStatus.OK);
    }

    @PostMapping("/addmoreproduct/{idstore}/{shoppingcart}/{idproduct}/{quantity}")
    public ResponseEntity<String> addMoreProduct(@PathVariable("idstore") Long idstore, @PathVariable("shoppingcart") Long shoppingcart, @PathVariable("idproduct") Long idproduct, @PathVariable("quantity") Integer quantity) throws Error {
        return new ResponseEntity<>(storeService.addMoreProductstoShopping(idstore, shoppingcart, idproduct, quantity), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCart(@PathVariable("id") Long id) throws Error {
        return new ResponseEntity<>(shoppingCartMapper.shoppingCartToDTO(storeService.getShoppingCart(id)), HttpStatus.OK);
    }

    @PostMapping("/{id}/checkout")
    public ResponseEntity<String> checkout(@PathVariable("id") Long id) throws Error, IOException {
        return new ResponseEntity<>(storeService.checkoOut(id), HttpStatus.OK);
    }

    @PatchMapping("/{idshoppingcart}/removeproduct/{idproduct}")
    public ResponseEntity<String> removeProduct(@PathVariable("idshoppingcart") Long idshoppingcart, @PathVariable("idproduct") Long idproduct) throws Error {
        return new ResponseEntity<>(storeService.deleteProductFromShoppingCart(idshoppingcart, idproduct), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCartDTO>> getAllShoppingCarts() throws Error {
        return new ResponseEntity<>(shoppingCartMapper.shoppingCartListToDTOList(storeService.getAllShoppingCart()), HttpStatus.OK);
    }

}
