package com.ecommerce.service;

import com.ecommerce.dto.ShowUserDTO;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.mapper.UserMapper;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    //TODO ENCRIPTAR CONTRASEÑA


    public User findById(Long characterId) throws Error {
        return userRepository.findById(characterId).orElseThrow(() -> new Error("User not found"));
    }

    public ShowUserDTO findByIdShowUser(Long id) throws Error {
        return userMapper.usertoShowDTO(userRepository.findById(id).orElseThrow(() -> new Error("User not found")));
    }

    public List<ShowUserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<ShowUserDTO> showUserDTOS = new ArrayList<>();
        for (User user : users) {
            showUserDTOS.add(userMapper.usertoShowDTO(user));
        }
        return showUserDTOS;
    }
    public ShowUserDTO findByNameShowUser(String name) throws Error {
        return userMapper.usertoShowDTO(userRepository.findByName(name).orElseThrow(() -> new Error("User not found")));
    }

    public List<ShowUserDTO> findByNameShowUserList(String name) throws Error {
        List <ShowUserDTO> lista = new ArrayList<>();
        if (userRepository.findByName(name).isPresent()){
            lista.add(userMapper.usertoShowDTO(userRepository.findByName(name).get()));
        }
        return lista;
    }

    public ShowUserDTO update(Long id, UserDTO user) throws Error {
        User user1 = userRepository.findById(id).orElseThrow(() -> new Error("User not found"));
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        return userMapper.usertoShowDTO(userRepository.save(user1));
    }

    public UserDTO save(UserDTO user) throws Error {
        Optional<User> userOptional = userRepository.findByName(user.getName());
        if (userOptional.isPresent()) {
            throw new Error("User already exists");
        }
        User nuevo = userRepository.save(userMapper.DTOtoUser(user));
        return userMapper.usertoDTO(nuevo);
    }

    public UserDTO setRole(Long id, Long role_id) throws Error {
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
