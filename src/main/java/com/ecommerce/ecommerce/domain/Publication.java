package domain;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Publication {
    private Long id;
    private String publicationName;
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
    public void activatePublication() {
        this.isActive = true;
    }

    public void deactivatePublication() {
        this.isActive = false;
    }
}
