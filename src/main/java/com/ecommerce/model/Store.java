package com.ecommerce.model;

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
    private Long idStore; // 5
    @OneToOne
    @JoinColumn(name="seller_id")
    private Seller user; // seller
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publication> publications; // Gorra, Zapatos, Pantalones
    @Column(name = "modos_de_pago")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PaymentMethod.class)
    private List<PaymentMethod> paymentMethods;

    
}
