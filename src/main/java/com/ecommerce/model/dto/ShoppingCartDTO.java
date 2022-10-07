package com.ecommerce.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ShoppingCartDTO {
    private Long idShoppingCart;
    private List<ItemShowDTO> products;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate buyDate;
    private Integer totalProducts;
    private Double totalPrice;

}
