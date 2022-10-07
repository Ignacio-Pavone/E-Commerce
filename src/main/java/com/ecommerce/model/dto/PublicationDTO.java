package com.ecommerce.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationDTO {
    private Long idPublication;
    private Long idSellProduct;
    private String publicationName;
    private String productName;
    private String sellerCustomization;
    private String stock;
    private Double price;
    private Boolean isActive;
    @JsonIgnore
    @JsonIgnoreProperties
    private Long id_store;
}
