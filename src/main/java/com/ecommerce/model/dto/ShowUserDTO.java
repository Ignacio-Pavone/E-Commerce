package com.ecommerce.model.dto;

import com.ecommerce.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowUserDTO {
    private Long id;
    private String name;
    @JsonProperty("role_id")
    private Role role_id;

}
