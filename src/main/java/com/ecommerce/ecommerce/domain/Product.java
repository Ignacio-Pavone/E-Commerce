package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter

public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String mangerName;
    private String name;
    private Double basePrice;
    private String description;
    private String manufacturingTime;

    private List<BaseCustomization> baseCustomizationOptions;

    public Product(String name, String mangerName, Double basePrice, String description, String manufacturingTime) {
        this.mangerName = mangerName;
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
        this.manufacturingTime = manufacturingTime;
        baseCustomizationOptions = new ArrayList<>();
    }

    public void addPersonalization(BaseCustomization... baseCustomization) {
        Collections.addAll(baseCustomizationOptions, baseCustomization);
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", mangerName='" + mangerName + '\'' +
                ", productName='" + name + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
