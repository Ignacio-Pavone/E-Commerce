package com.ecommerce.dto;

import com.ecommerce.model.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimpleStoreDTO {
    private String sellername;
    private List<SimplePublicationDTO> publications;
    private List<PaymentMethod> paymentMethods;

}
