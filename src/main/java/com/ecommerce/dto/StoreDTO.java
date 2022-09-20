package com.ecommerce.dto;

import com.ecommerce.model.PaymentMethod;
import com.ecommerce.model.Publication;
import com.ecommerce.model.Seller;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class StoreDTO {
    private Long idStore;
    private Long seller_id;
    private String sellername;
    private List<Publication> publications;
    private List<PaymentMethod> paymentMethods;
}
