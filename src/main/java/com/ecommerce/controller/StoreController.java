package com.ecommerce.controller;
import com.ecommerce.converters.PaymentConverter;
import com.ecommerce.exception.Error;
import com.ecommerce.model.Store;
import com.ecommerce.model.dto.PublicationDTO;
import com.ecommerce.model.dto.StoreDTO;
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
    public ResponseEntity<StoreDTO> getStorebyID(@PathVariable Long id) throws Error {
        return new ResponseEntity<>(storeService.findbyId(id), HttpStatus.FOUND);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<StoreDTO> createStore(@PathVariable("id") Long id) throws Error {
        return new ResponseEntity<>(storeService.createStore(id), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StoreDTO> deleteStore(@PathVariable("id") Long id) throws Error {
        return new ResponseEntity<>(storeService.deleteStore(id), HttpStatus.OK);
    }

    @PostMapping("/addpayment/{id}")
    public ResponseEntity<StoreDTO> addPayment(@PathVariable("id") Long id, @RequestBody String payment) throws Error {
        PaymentConverter paymentConverter = new PaymentConverter();
        return new ResponseEntity<>(storeService.addPaymentMethod(id, paymentConverter.convertToEntityAttribute(payment.toLowerCase())), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deletepayment/{id}")
    public ResponseEntity<StoreDTO> deletePayment(@PathVariable("id") Long id, @RequestBody String payment) throws Error {
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





}
