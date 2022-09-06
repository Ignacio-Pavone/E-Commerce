package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class SellProduct {
    private Product product;
    private Double sellingPrice;
    private List<SellerCustomization> baseCustomizationOptions;

    public SellProduct(Product product, Double sellingPrice) {
        this.baseCustomizationOptions = new ArrayList<>();
        this.product = product;
        this.sellingPrice = sellingPrice;
    }

    public void addPersonalization(SellerCustomization... baseCustomization){
        Collections.addAll(baseCustomizationOptions, baseCustomization);
    }

    public void setSellingPrice(Double basePrice,List<SellerCustomization> customizations) {
        Double acumulator = 0.0;
        for (SellerCustomization customization : customizations) {
            acumulator += customization.getPrice();
        }
        this.sellingPrice = acumulator + basePrice;
    }

    @Override
    public String toString() {
        return "SellProduct{" +
                "product=" + product +
                ", sellingPrice=" + sellingPrice +
                ", baseCustomizationOptions=" + baseCustomizationOptions +
                '}';
    }
}
