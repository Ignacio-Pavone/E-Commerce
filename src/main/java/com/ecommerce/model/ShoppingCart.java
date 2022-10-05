package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "shopping_cart")
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_shopping_cart;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "shopping_cart_sell_product",
            joinColumns = @JoinColumn(name = "id_shopping_cart"),
            inverseJoinColumns = @JoinColumn(name = "id_sell_product"))
    private List<Item> productList = new ArrayList<>();
    private Integer totalProducts = 0;
    private Double totalPrice = 0.0;

    private Long Store;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate;

    public ShoppingCart(List<Item> productList, Integer totalProducts, Double totalPrice, Long store, LocalDate buyDate) {
        this.productList = productList;
        this.totalProducts = totalProducts;
        this.totalPrice = totalPrice;
        Store = store;
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id_shopping_cart=" + id_shopping_cart +
                ", productList=" + productList +
                ", totalProducts=" + totalProducts +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
