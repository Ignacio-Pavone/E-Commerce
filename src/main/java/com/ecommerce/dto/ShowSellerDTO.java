package com.ecommerce.dto;

import com.ecommerce.model.Role;
import com.ecommerce.model.SellProduct;
import lombok.Data;
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
