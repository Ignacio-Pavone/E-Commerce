package com.ecommerce.service;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.exception.NotFound;
import com.ecommerce.mapper.UserMapper;
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
    @Autowired
    private UserMapper userMapper;



    //TODO ENCRIPTAR CONTRASEÑA

    public User findById(Long characterId) throws NotFound {
        return userRepository.findById(characterId).orElseThrow(() -> new NotFound("User not found"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByName(String name) {
        return userRepository.findByName(name).orElseThrow(RuntimeException::new);
    }

    public UserDTO save(UserDTO user) {
        Optional<User> userOptional = userRepository.findByName(user.getName());
        if (userOptional.isPresent()) {

        }
        User nuevo = userRepository.save(userMapper.DTOtoUser(user));
        return userMapper.usertoDTO(nuevo);
    }

    public UserDTO setRole(Long id, Long role_id) throws NotFound {
        User user = findById(id);
        user.setRole_id(role_id);
        return userMapper.usertoDTO(userRepository.save(user));
    }

    public boolean deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return true;
        }
        return false;
    }

}
