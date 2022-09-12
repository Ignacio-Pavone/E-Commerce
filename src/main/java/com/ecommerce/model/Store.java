package com.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStore; // 5
    @OneToOne
    @JoinColumn(name="user_id")
    private User user; // Nacho
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publication> publications; // Gorra, Zapatos, Pantalones
    @Column(name = "modos_de_pago")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PaymentMethod.class)
    private List<PaymentMethod> paymentMethods;

    public Store(User user, List<Publication> publications, List<PaymentMethod> paymentMethods) {
        this.user = user;
        this.publications = publications;
        this.paymentMethods = paymentMethods;
    }

    public Store() {

    }
}
