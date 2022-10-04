package com.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemShowDTO {
    private ShowSellProductDTO producto;
    private Integer quantity;
}
