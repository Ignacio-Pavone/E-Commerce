package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames = "user_id"))
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seller_id;
    @JoinColumn(name = "user_id", referencedColumnName = "id",insertable=false, updatable=false)
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @JsonProperty("user_id")
    @JsonIgnoreProperties("user_id")
    @JsonBackReference
    private Long user_id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seller_id")
    private List<SellProduct> sellProducts;


    public void addSellProduct(SellProduct sellProduct) {
        sellProducts.add(sellProduct);
    }

}
