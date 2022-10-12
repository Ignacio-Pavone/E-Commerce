package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
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
    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    }
    )
    @JoinTable(name = "shopping_cart_item",
            joinColumns = @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))

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
