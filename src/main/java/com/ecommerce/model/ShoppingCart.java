package com.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity (name = "shopping_cart")
public class ShoppingCart {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id_shopping_cart;
   @ManyToMany(cascade = CascadeType.ALL)
   private List<SellProduct> productList;
   private Integer totalProducts;
   private Double totalPrice;
}
