package com.ecommerce.mapper;
import com.ecommerce.model.dto.ShowUserDTO;
import com.ecommerce.model.dto.UserDTO;
import com.ecommerce.model.User;
import com.ecommerce.model.dto.UserRegisterDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public ShowUserDTO usertoShowDTO(User user) {
        ShowUserDTO showUserDTO = new ShowUserDTO();
        showUserDTO.setId(user.getId());
        showUserDTO.setName(user.getName());
        showUserDTO.setRole_id(user.getRole());
        return showUserDTO;
    }
    public UserDTO usertoDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole_id(user.getRole_id());
        return userDTO;
    }

    public User DTOtoUser(UserRegisterDTO userDTO){
        User user = new User();
        user.setName(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole_id(userDTO.getRole());
        return user;
    }

}

