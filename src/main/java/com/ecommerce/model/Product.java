
package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity(name="productos")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double basePrice;
    private String description;
    private String manufacturingTime;
    @JoinColumn(name = "custom_id", referencedColumnName = "id",nullable = true,insertable=false, updatable=false)
    @ManyToOne(targetEntity= com.ecommerce.model.BaseCustomization.class)
    private BaseCustomization baseCustomizationOptions;

    @JsonProperty("custom_id")
    @JsonIgnoreProperties("custom_id")
    @JsonBackReference
    private Long custom_id;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", description='" + description + '\'' +
                ", manufacturingTime='" + manufacturingTime + '\'' +
                ", baseCustomizationOptions=" + baseCustomizationOptions +
                ", custom_id=" + custom_id +
                '}';
    }
}