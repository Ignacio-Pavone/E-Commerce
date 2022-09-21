package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name= "sell_products")
public class SellProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Double sellingPrice;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sellproduct_id")
    private List<SellerCustomization> sellerCustomizations;

    public void addPersonalization(SellerCustomization... baseCustomization){
        Collections.addAll(sellerCustomizations, baseCustomization);
    }

    public void setSellingPrice(Double basePrice,List<SellerCustomization> customizations) {
        Double acumulator = 0.0;
        for (SellerCustomization customization : customizations) {
            acumulator += customization.getPrice();
        }
        this.sellingPrice = acumulator + basePrice;
    }


}
