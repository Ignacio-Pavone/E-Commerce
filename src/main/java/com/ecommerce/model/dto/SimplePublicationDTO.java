package com.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimplePublicationDTO {
    private String publicationName;
    private String productName;
    private Boolean isActive;
    private Double price;
    private String stock;
}
