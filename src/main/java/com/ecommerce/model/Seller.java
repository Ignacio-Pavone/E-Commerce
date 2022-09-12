package com.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Seller {
    private User user;
    private List<SellProduct> sellProducts;

    public Seller(User user, List<SellProduct> sellProducts) {
        this.user = user;
        this.sellProducts = sellProducts;
    }

}
