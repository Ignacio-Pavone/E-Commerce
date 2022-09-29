package com.ecommerce.controller;


import com.ecommerce.dto.*;
import com.ecommerce.exception.Error;
import com.ecommerce.model.Seller;
import com.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/seller")
@RestController
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("{id}")
    public ResponseEntity<ShowSellerDTO> getSeller(@PathVariable("id") Long id) throws Error {
        return new ResponseEntity<>(sellerService.findSellerByIdDTO(id), HttpStatus.FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSeller(@PathVariable("id") Long id) throws Error {
        if (sellerService.deleteSeller(id)) {
            return new ResponseEntity<>("Seller DELETED", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Seller NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Seller> saveSeller(@RequestBody Seller seller) throws Error {
        return new ResponseEntity<>(sellerService.saveSeller(seller), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<ShowSellerDTO>> getAllsellers() {
        return new ResponseEntity<>(sellerService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/buy")
    public ResponseEntity<ShowSellProductDTO> buyProduct(@RequestBody @Valid SellerProductAddDTO productToBuy) throws Error {
        return new ResponseEntity<>(sellerService.buyProductbySeller(productToBuy), HttpStatus.CREATED);
    }


}
