package com.ecommerce.mapper;

import com.ecommerce.dto.ShowSellerDTO;
import com.ecommerce.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SellerMapper {
    @Autowired
    private UserMapper userMapper;
    public ShowSellerDTO toShowSellerDTO(Seller seller) {
        ShowSellerDTO showSellerDTO = new ShowSellerDTO();
        showSellerDTO.setSeller_id(seller.getSeller_id());
        showSellerDTO.setUsername(seller.getUser().getName());
        showSellerDTO.setRole(seller.getUser().getRole().getRole().name());
        showSellerDTO.setSellProducts(seller.getSellProducts());
        return showSellerDTO;
    }

}
