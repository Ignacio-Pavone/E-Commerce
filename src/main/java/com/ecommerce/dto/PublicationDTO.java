package com.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationDTO {
    private Long id;
    private String publicationName;
    private String productName;
    private Long idSellProduct;
    private String stock;
    private Double price;
    private Boolean isActive;
    @JsonIgnore
    @JsonIgnoreProperties
    private Long id_store;
}
