package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String publicationName;
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name="id_sellproduct",nullable = true,insertable=false, updatable=false)
    private SellProduct sellProduct;

    @JsonProperty("id_sellproduct")
    @JsonIgnoreProperties("id_sellproduct")
    @JsonBackReference
    private Long id_sellproduct;
    private String stock;
    private Double price;
    private Boolean isActive;
    @JsonProperty("store_id")
    @JsonIgnoreProperties("store_id")
    @JsonBackReference
    private Long store_id;

    public void activatePublication() {
        this.isActive = true;
    }
    public void deactivatePublication() {
        this.isActive = false;
    }


    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", publicationName='" + publicationName + '\'' +
                ", sellProduct=" + sellProduct +
                ", id_sellproduct=" + id_sellproduct +
                ", stock='" + stock + '\'' +
                ", price=" + price +
                ", isActive=" + isActive +
                ", store_id=" + store_id +
                '}';
    }
}
