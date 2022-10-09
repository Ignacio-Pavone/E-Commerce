package com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStore;
    @OneToOne
    @JoinColumn(name = "seller_id")
    private Seller user;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //todo SOLUCIONAR ERRRO AL ELIMINAR STORE
    @JoinColumn(name = "store_id")
    private List<Publication> publications;
    @Column(name = "modos_de_pago")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PaymentMethod.class, fetch = FetchType.EAGER)
    private List<PaymentMethod> paymentMethods;

    @Override
    public String toString() {
        return "Store{" +
                "idStore=" + idStore +
                ", user=" + user +
                ", publications=" + publications +
                ", paymentMethods=" + paymentMethods +
                '}';
    }
}
