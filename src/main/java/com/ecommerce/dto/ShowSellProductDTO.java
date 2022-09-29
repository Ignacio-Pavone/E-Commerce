package com.ecommerce.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowSellProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String description;
}
