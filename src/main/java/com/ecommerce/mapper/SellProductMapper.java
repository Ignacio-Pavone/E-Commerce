package com.ecommerce.mapper;

import com.ecommerce.dto.ShowSellProductDTO;
import com.ecommerce.model.Product;
import com.ecommerce.model.SellProduct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<ShowSellProductDTO> sellproductEntityList2DTOList(List<Product> sellProducts) {
        List<ShowSellProductDTO> sellProductDTOList = new ArrayList<>();
        for (Product sellProduct : sellProducts) {
            ShowSellProductDTO sellProductDTO = new ShowSellProductDTO();
            sellProductDTO.setId(sellProduct.getId());
            sellProductDTO.setName(sellProduct.getName());
            sellProductDTO.setPrice(sellProduct.getBasePrice());
            sellProductDTO.setDescription(sellProduct.getDescription());
            sellProductDTOList.add(sellProductDTO);
        }
        return sellProductDTOList;
    }
}
