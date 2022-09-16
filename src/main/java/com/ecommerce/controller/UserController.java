package com.ecommerce.controller;

import com.ecommerce.dto.ShowUserDTO;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.exception.NotFound;
import com.ecommerce.model.Seller;
import com.ecommerce.model.User;
import com.ecommerce.service.SellerService;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SellerService sellerService;

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) throws NotFound {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/minimal/{id}")
    public ResponseEntity<ShowUserDTO> findByIdShowUser(@PathVariable Long id) throws NotFound {
        return new ResponseEntity<>(userService.findByIdShowUser(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ShowUserDTO> findByName(@PathVariable String name) {
        return new ResponseEntity<>(userService.findByNameShowUser(name), HttpStatus.OK);
    }

    /*
    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserbyUsername(@PathVariable("name") String name) {
        return new ResponseEntity<>(userService.findByName(name), HttpStatus.OK);
    }
    */
    @PostMapping()
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        if (userService.deleteUser(id)) {
            return new ResponseEntity<>("User DELETED", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User NOT FOUND", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/setrole/{id}")
    public ResponseEntity<UserDTO> setRole(@PathVariable("id") Long id, @RequestBody UserDTO user) throws NotFound {
        return new ResponseEntity<>(userService.setRole(id, user.getRole_id()), HttpStatus.OK);
    }

    @GetMapping("/seller/{id}")
    public ResponseEntity<Seller> getSeller(@PathVariable("id") Long id) throws NotFound {
        return new ResponseEntity<>(sellerService.findSellerById(id), HttpStatus.OK);
    }

    @PostMapping("/seller")
    public ResponseEntity<Seller> saveSeller(@RequestBody Seller seller) {
        return new ResponseEntity<>(sellerService.saveSeller(seller), HttpStatus.CREATED);
    }

    @GetMapping("/seller")
    public ResponseEntity<List<Seller>> getAllsellers() {
        return new ResponseEntity<>(sellerService.findAll(), HttpStatus.OK);
    }
}


