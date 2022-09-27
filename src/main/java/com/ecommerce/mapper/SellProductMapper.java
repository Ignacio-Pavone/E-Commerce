package com.ecommerce.mapper;

import com.ecommerce.dto.ShowSellProductDTO;
import com.ecommerce.model.SellProduct;
import org.springframework.stereotype.Component;

@Component
public class SellProductMapper {
    public ShowSellProductDTO sellProductToDTO(SellProduct sellProduct) {
        ShowSellProductDTO sellProductDTO = new ShowSellProductDTO();
        sellProductDTO.setId(sellProduct.getId());
        sellProductDTO.setName(sellProduct.getProduct().getName());
        sellProductDTO.setPrice(sellProduct.getSellingPrice());
        sellProductDTO.setDescription(sellProduct.getProduct().getDescription());
        return sellProductDTO;
    }
}
