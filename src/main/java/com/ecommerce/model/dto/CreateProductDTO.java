package com.ecommerce.model.dto;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreateProductDTO {
    private String name;
    private Double basePrice;
    private String description;
    private String manufacturingTime;

}
