package com.ecommerce.model.dto;

import com.ecommerce.model.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StoreDTO {
    private Long idStore;
    private Long seller_id;
    private String sellername;
    private List<PublicationDTO> publications;
    private List<PaymentMethod> paymentMethods;
}
