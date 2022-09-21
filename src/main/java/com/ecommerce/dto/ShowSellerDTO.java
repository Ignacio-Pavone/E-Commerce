package com.ecommerce.dto;

import com.ecommerce.model.SellProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ShowSellerDTO {
    private Long seller_id;
    private ShowUserDTO user;
    private List<SellProduct> sellProducts;
}
