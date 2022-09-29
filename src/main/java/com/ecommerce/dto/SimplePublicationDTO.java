package com.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimplePublicationDTO {
    private String publicationName;
    private Boolean isActive;
    private Double price;
    private String stock;
}
