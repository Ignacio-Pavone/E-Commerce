package com.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShowSellerDTO {
    private Long seller_id;
    private String username;
    private String role;
    private List<ShowSellProductDTO> sellProducts;
}
