package com.ecommerce.mapper;

import com.ecommerce.model.dto.ShowSellerDTO;
import com.ecommerce.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SellerMapper {
    @Autowired
    private SellProductMapper sellProductMapper;
    public ShowSellerDTO toShowSellerDTO(Seller seller) {
        ShowSellerDTO showSellerDTO = new ShowSellerDTO();
        showSellerDTO.setSeller_id(seller.getSeller_id());
        showSellerDTO.setUsername(seller.getUser().getName());
        showSellerDTO.setRole(seller.getUser().getRole().getRole().name());
        showSellerDTO.setSellProducts(sellProductMapper.showSellProductDTOSlist2(seller.getSellProducts()));
        return showSellerDTO;
    }

}
