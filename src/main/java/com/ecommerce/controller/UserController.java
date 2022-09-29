package com.ecommerce.controller;

import com.ecommerce.dto.*;
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

    @GetMapping("{id}")
    public ResponseEntity<ShowUserDTO> findById(@PathVariable Long id) throws Error {
        return new ResponseEntity<>(userService.findByIdShowUser(id), HttpStatus.FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<ShowUserDTO>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    //TODO: me devuelve 2 necesito lista
    @GetMapping(params = "name")
    public ResponseEntity<List<ShowUserDTO>> findCharacterByName (@RequestParam(value = "name", required = false) String name) throws Error {
        if (name == null) {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userService.findByNameShowUserList(name), HttpStatus.OK);
        }
    }

    @PutMapping ("{id}")
    public ResponseEntity<ShowUserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO user) throws Error {
        return new ResponseEntity<>(userService.update(id, user), HttpStatus.OK);
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


}


