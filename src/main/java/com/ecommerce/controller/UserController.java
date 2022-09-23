package com.ecommerce.controller;

import com.ecommerce.dto.ShowSellerDTO;
import com.ecommerce.dto.ShowUserDTO;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.model.Seller;
import com.ecommerce.service.SellerService;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SellerService sellerService;

    @GetMapping("{id}")
    public ResponseEntity<ShowUserDTO> findById(@PathVariable Long id) throws Error {
        return new ResponseEntity<>(userService.findByIdShowUser(id), HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<ShowUserDTO>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ShowUserDTO> findByName(@PathVariable String name) throws Error {
        return new ResponseEntity<>(userService.findByNameShowUser(name), HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO user) throws Error {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        if (userService.deleteUser(id)) {
            return new ResponseEntity<>("User DELETED", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/setrole/{id}")
    public ResponseEntity<UserDTO> setRole(@PathVariable("id") Long id, @RequestBody UserDTO user) throws Error {
        return new ResponseEntity<>(userService.setRole(id, user.getRole_id()), HttpStatus.ACCEPTED);
    }

    @GetMapping("/seller/{id}")
    public ResponseEntity<ShowSellerDTO> getSeller(@PathVariable("id") Long id) throws Error {
        return new ResponseEntity<>(sellerService.findSellerByIdDTO(id), HttpStatus.FOUND);
    }

    @PostMapping("/seller")
    public ResponseEntity<Seller> saveSeller(@RequestBody Seller seller) throws Error {
        return new ResponseEntity<>(sellerService.saveSeller(seller), HttpStatus.CREATED);
    }

    @GetMapping("/seller")
    public ResponseEntity<List<Seller>> getAllsellers() {
        return new ResponseEntity<>(sellerService.findAll(), HttpStatus.OK);
    }

}


