package com.ecommerce.dto;

import com.ecommerce.model.PaymentMethod;
import com.ecommerce.model.Publication;
import com.ecommerce.model.Seller;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import lombok.Data;
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
