package com.ecommerce.mapper;

import com.ecommerce.dto.UserDTO;

import com.ecommerce.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserMapper {

    public UserDTO usertoDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole_id(user.getRole_id());
        return userDTO;
    }
    public User DTOtoUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setRole_id(userDTO.getRole_id());
        return user;
    }

}

