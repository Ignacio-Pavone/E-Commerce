package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private SellProduct sellProduct;
    private Integer quantity;


    public Item(SellProduct sellProduct, Integer quantity) {
        this.sellProduct = sellProduct;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", sellProduct=" + sellProduct +
                ", quantity=" + quantity +
                '}';
    }
}
