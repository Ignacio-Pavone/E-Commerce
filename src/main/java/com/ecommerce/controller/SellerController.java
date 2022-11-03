package com.ecommerce.controller;
import com.ecommerce.exception.Error;
import com.ecommerce.model.Seller;
import com.ecommerce.model.dto.SellerProductAddDTO;
import com.ecommerce.model.dto.ShowSellProductDTO;
import com.ecommerce.model.dto.ShowSellerDTO;
import com.ecommerce.service.SellerService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Get Seller by id")
    @GetMapping("{id}")
    public ResponseEntity<ShowSellerDTO> getSeller(@PathVariable("id") Long id) throws Error {
        return new ResponseEntity<>(sellerService.findSellerByIdDTO(id), HttpStatus.FOUND);
    }
    @ApiOperation(value = "Delete Seller")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSeller(@PathVariable("id") Long id) throws Error {
        if (sellerService.deleteSeller(id)) {
            return new ResponseEntity<>("Seller DELETED", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Seller NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }
    @ApiOperation(value = "Add new Seller")
    @PostMapping("")
    public ResponseEntity<String> saveSeller(@RequestBody Seller seller) {
        try {
            return new ResponseEntity<>(sellerService.saveSeller(seller), HttpStatus.CREATED);
        } catch (Error e) {
            return new ResponseEntity<>("User is not a Seller",HttpStatus.BAD_REQUEST);
        }
    }
    @ApiOperation(value = "Get all Sellers")
    @GetMapping("")
    public ResponseEntity<List<ShowSellerDTO>> getAllsellers() {
        return new ResponseEntity<>(sellerService.findAll(), HttpStatus.OK);
    }
    @ApiOperation(value = "Add Product to Seller")
    @PostMapping("/{idSeller}")
    public ResponseEntity<ShowSellProductDTO> buyProductr(@PathVariable("idSeller") Long idSeller, @RequestBody @Valid SellerProductAddDTO productToBuy) throws Error {
        return new ResponseEntity<>(sellerService.buyProductbySeller(productToBuy, idSeller), HttpStatus.CREATED);
    }
}
