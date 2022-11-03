package com.ecommerce.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartProductDTO {
    private long storeID;
    private long publicationID;
    private int quantity;
}
