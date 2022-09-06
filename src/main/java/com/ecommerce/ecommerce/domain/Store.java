package domain;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class Store {
    private Long idStore; // 5
    private User user; // Nacho
    private List<Publication> publications; // Gorra, Zapatos, Pantalones
    private List<PaymentMethod> paymentMethods;

    public Store(User user, List<Publication> publications, List<PaymentMethod> paymentMethods) {
        this.user = user;
        this.publications = publications;
        this.paymentMethods = paymentMethods;
    }

}
