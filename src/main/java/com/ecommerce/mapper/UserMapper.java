package com.ecommerce.mapper;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.model.User;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO usertoUserDTO(User user);
}

