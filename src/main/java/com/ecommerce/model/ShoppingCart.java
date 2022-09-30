package com.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity (name = "shopping_cart")
public class ShoppingCart {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id_shopping_cart;

   @ManyToMany (fetch = FetchType.LAZY)
   @JoinTable(name = "shopping_cart_sell_product",
           joinColumns = @JoinColumn(name = "id_shopping_cart"),
           inverseJoinColumns = @JoinColumn(name = "id_sell_product"))
   private List<SellProduct> productList = new ArrayList<>();
   private Integer totalProducts = 0;
   private Double totalPrice = 0.0;

}
