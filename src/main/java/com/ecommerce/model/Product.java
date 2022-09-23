package com.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity(name="productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double basePrice;
    private String description;
    private String manufacturingTime;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<BaseCustomization> baseCustomizationOptions;

    public Product(String name, Double basePrice, String description, String manufacturingTime) {
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
        this.manufacturingTime = manufacturingTime;
        baseCustomizationOptions = new ArrayList<>();
    }

    public Product() {
    }
    public void addPersonalization(BaseCustomization... baseCustomization) {
        Collections.addAll(baseCustomizationOptions, baseCustomization);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +

                ", productName='" + name + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
