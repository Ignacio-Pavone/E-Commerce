package com.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShoppingCartDTO {
    private Long id_shopping_cart;
    private List<ShowSellProductDTO> product;
    private Integer totalProducts;
    private Double totalPrice;

}
