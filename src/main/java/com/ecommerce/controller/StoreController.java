package com.ecommerce.controller;


import com.ecommerce.converters.PaymentConverter;
import com.ecommerce.dto.PublicationDTO;
import com.ecommerce.dto.SellerProductAddDTO;
import com.ecommerce.dto.SimpleStoreDTO;
import com.ecommerce.dto.StoreDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.model.SellProduct;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.model.Store;
import com.ecommerce.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/stores")
@RestController
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping()
    public ResponseEntity<List<StoreDTO>> getAll() {
        return new ResponseEntity<>(storeService.findall(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleStoreDTO> getStorebyID(@PathVariable Long id) throws Error {
        return new ResponseEntity<>(storeService.findbyId(id), HttpStatus.FOUND);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Store> createStore(@PathVariable("id") Long id) throws Error {
        return new ResponseEntity<>(storeService.createStore(id), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Store> deleteStore(@PathVariable("id") Long id) throws Error {
        return new ResponseEntity<>(storeService.deleteStore(id), HttpStatus.OK);
    }

    @PostMapping("/addpayment/{id}")
    public ResponseEntity<Store> addPayment(@PathVariable("id") Long id, @RequestBody String payment) throws Error {
        PaymentConverter paymentConverter = new PaymentConverter();
        return new ResponseEntity<>(storeService.addPaymentMethod(id, paymentConverter.convertToEntityAttribute(payment.toLowerCase())), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deletepayment/{id}")
    public ResponseEntity<Store> deletePayment(@PathVariable("id") Long id, @RequestBody String payment) throws Error {
        PaymentConverter paymentConverter = new PaymentConverter();
        return new ResponseEntity<>(storeService.removePaymentMethod(id, paymentConverter.convertToEntityAttribute(payment.toLowerCase())), HttpStatus.ACCEPTED);
    }

    @PostMapping("/addpublication/{idstore}")
    public ResponseEntity<PublicationDTO> addPublication(@PathVariable("idstore") Long id, @RequestBody PublicationDTO publication) throws Error {
        return new ResponseEntity<>(storeService.addPublication(id, publication), HttpStatus.OK);
    }

    @PostMapping ("/{idstore}/publications/{idpublication}/{state}")
    public ResponseEntity<StoreDTO> updatePublication(@PathVariable("idstore") Long idstore, @PathVariable("idpublication") Long idpublication, @PathVariable("state") Boolean state) throws Error {
        return new ResponseEntity<>(storeService.updatepublicationStatus(idstore, idpublication, state), HttpStatus.OK);
    }

    @PostMapping("/{idstore}/shoppingcart/{idproduct}")
    public ResponseEntity<String> addProductToShoppingCart(@PathVariable("idstore") Long idstore, @PathVariable("idproduct") Long idproduct) throws Error {
        return new ResponseEntity<>(storeService.addProductToShoppingCart(idstore, idproduct), HttpStatus.OK);
    }

    @GetMapping("/shoppingcart")
    public ResponseEntity<List<ShoppingCart>> getShoppingCart() throws Error {
        return new ResponseEntity<>(storeService.getShoppingCart(), HttpStatus.OK);
    }

}
