package com.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemShowDTO {
    private ShowSellProductDTO product;
    private Integer quantity;
}
