package com.ecommerce.controller;
import com.ecommerce.dto.UserDTO;

import com.ecommerce.exception.NotFound;
import com.ecommerce.model.User;
import com.ecommerce.service.UserService;
import com.ecommerce.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
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
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserbyId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
        } catch (NotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserbyUsername(@PathVariable("name") String name) {
        return new ResponseEntity<>(userService.findByName(name), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        System.out.println(user);
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
