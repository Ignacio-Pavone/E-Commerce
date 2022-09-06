package domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerCustomization {
    private String name;
    private Double price;
    private String description;


    public SellerCustomization(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return "SellerCustomization{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
