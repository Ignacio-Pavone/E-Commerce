package com.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerProductAddDTO {
    private Long idProduct;
    private Long idSeller;
    private Long idCustomization;
    private String name;
    private Double price;
    private String description;
}
