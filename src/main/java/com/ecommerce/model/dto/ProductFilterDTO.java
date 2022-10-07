package com.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilterDTO {
    private Long id;
    private String name;
    private String order;

    public ProductFilterDTO(String name, String order) {
        this.name = name;
        this.order = order;
    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    // orden descendente
    public boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
