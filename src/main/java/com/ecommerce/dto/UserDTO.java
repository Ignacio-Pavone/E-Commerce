package com.ecommerce.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDTO{
    @JsonProperty("id")
    private Long id;
    @NotBlank
    @JsonProperty("name")
    private String name;
    @JsonProperty("password")
    @NotBlank
    private String password;
    @JsonProperty("role_id")
    @NotBlank
    private Long role_id;
}
