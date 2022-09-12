package com.ecommerce.service;

import com.ecommerce.exception.NotFound;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(Long characterId) throws NotFound {
        return userRepository.findById(characterId).orElseThrow(() -> new NotFound("User not found"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByName(String name) {
        return userRepository.findByName(name).orElseThrow(RuntimeException::new);
    }

    public User save(User user) {
        Optional<User> userOptional = userRepository.findByName(user.getName());
        if (userOptional.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }

}
