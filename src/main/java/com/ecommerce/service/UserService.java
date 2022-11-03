package com.ecommerce.service;

import com.ecommerce.model.Role;
import com.ecommerce.model.RoleType;
import com.ecommerce.model.dto.ShowUserDTO;
import com.ecommerce.model.dto.UserDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.mapper.UserMapper;
import com.ecommerce.model.User;
import com.ecommerce.model.dto.UserRegisterDTO;
import com.ecommerce.repository.RoleRepository;
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
    @Autowired
    private RoleRepository roleRepository;


    public User findById(Long characterId) throws Error {
        return userRepository.findById(characterId).orElseThrow(() -> new Error("User not found"));
    }

    public ShowUserDTO findByIdShowUser(Long id) throws Error {
        return userMapper.usertoShowDTO(userRepository.findById(id).orElseThrow(() -> new Error("User not found")));
    }

    public List<ShowUserDTO> findAll( ) {
        List<ShowUserDTO> showUserDTOList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            showUserDTOList.add(userMapper.usertoShowDTO(user));
        }
        return showUserDTOList;
    }

    public List<ShowUserDTO> findByNameShowUserList(String name) throws Error {
        List <ShowUserDTO> lista = new ArrayList<>();
        for (User user : userRepository.findAllByName(name)) {
            lista.add(userMapper.usertoShowDTO(user));
        }
        return lista;
    }

    public ShowUserDTO update(Long id, UserDTO user) throws Error {
        User user1 = userRepository.findById(id).orElseThrow(() -> new Error("User not found"));
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        return userMapper.usertoShowDTO(userRepository.save(user1));
    }

    public UserDTO save(UserRegisterDTO user) throws Error {
        Optional<User> user1 = userRepository.findByName(user.getUsername());
        User nuevo = new User();
        if (user1.isPresent()) {
            return null;
        }else{
            user.setRole(2L);
            nuevo = userRepository.save(userMapper.DTOtoUser(user));
        }
        return userMapper.usertoDTO(nuevo);
    }

    public UserDTO setRole(Long id, Long role_id) throws Error {
        User user = findById(id);
        Optional<Role>  role = roleRepository.findById(role_id);
        if (role.isPresent()) {
            user.setRole_id(role_id);
            return userMapper.usertoDTO(userRepository.save(user));
        }
        return null;

    }

    public boolean deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return true;
        }
        return false;
    }

    public User findByName(String name) {
        Optional<User> nuevo = userRepository.findByName(name);
        if (nuevo.isPresent()) {
            return nuevo.get();
        }
        return null;
    }
    public boolean addRole(RoleType rol) {
        Role role = new Role();
        role.setRole(rol);
        Optional<Role> roleOptional = roleRepository.findByRole(rol);
        if (!roleOptional.isPresent()) {
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    public List<Role> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }
}
