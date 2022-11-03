package com.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerProductAddDTO {
    private Long idProduct;
    private String name;
    private Double price;
    private String seller_customization;
}
