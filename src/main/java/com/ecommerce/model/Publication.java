package com.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String publicationName;
    @OneToOne
    @JoinColumn(name="id_sellproduct")
    private SellProduct sellProduct; // gorra
    private String stock;
    private Double price;
    private Boolean isActive;
    public Publication(String publicationName, SellProduct sellProduct, String stock, Double price) {
        this.publicationName = publicationName;
        this.sellProduct = sellProduct;
        this.stock = stock;
        this.price = price;
        this.isActive = false;
    }

    public Publication() {

    }

    public void activatePublication() {
        this.isActive = true;
    }

    public void deactivatePublication() {
        this.isActive = false;
    }
}
