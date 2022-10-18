package com.ecommerce.controller;

import com.ecommerce.converters.RoleConverter;
import com.ecommerce.exception.Error;
import com.ecommerce.model.RoleType;
import com.ecommerce.model.dto.ShowUserDTO;
import com.ecommerce.model.dto.UserDTO;
import com.ecommerce.model.dto.UserRegisterDTO;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("{id}")
    public ResponseEntity<ShowUserDTO> findById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.findByIdShowUser(id), HttpStatus.FOUND);
        } catch (Error e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<ShowUserDTO>> getAll(@RequestParam(value = "name", required = false) String name) throws Error {
        if (name == null || userService.findByNameShowUserList(name).isEmpty()) {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userService.findByNameShowUserList(name), HttpStatus.OK);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ShowUserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO user) {
        try {
            return new ResponseEntity<>(userService.update(id, user), HttpStatus.OK);
        } catch (Error e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserRegisterDTO user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        } catch (Error e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
        if (userService.setRole(id, user.getRole_id()) != null) {
            return new ResponseEntity<>(userService.setRole(id, user.getRole_id()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/role")
    public ResponseEntity<String> addRole(@RequestBody String rol) throws Error {
        RoleConverter roleConverter = new RoleConverter();
        if (userService.addRole(roleConverter.convertToEntityAttribute(rol))) {
            return new ResponseEntity<>("Role ADDED", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Role is present or not correct", HttpStatus.BAD_REQUEST);
        }
    }
}


