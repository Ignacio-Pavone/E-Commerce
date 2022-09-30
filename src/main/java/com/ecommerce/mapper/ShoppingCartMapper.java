package com.ecommerce.mapper;

import com.ecommerce.dto.ShoppingCartDTO;
import com.ecommerce.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingCartMapper {
    @Autowired
    private SellProductMapper sellProductMapper;

    public ShoppingCartDTO shoppingCartToDTO(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setId_shopping_cart(shoppingCart.getId_shopping_cart());
        shoppingCartDTO.setProduct(sellProductMapper.showSellProductDTOSlist2(shoppingCart.getProductList()));
        shoppingCartDTO.setTotalPrice(shoppingCart.getTotalPrice( ));
        return shoppingCartDTO;
    }

    public ShoppingCart dtoToShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId_shopping_cart(shoppingCartDTO.getId_shopping_cart());
        shoppingCart.setTotalProducts(shoppingCartDTO.getTotalProducts());
        shoppingCart.setTotalPrice(shoppingCartDTO.getTotalPrice());
        return shoppingCart;
    }

    public List<ShoppingCartDTO> shoppingCartEntityList2DTOList(List<ShoppingCart> shoppingCarts) {
        List<ShoppingCartDTO> shoppingCartDTOList = new ArrayList<>();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
            shoppingCartDTO.setId_shopping_cart(shoppingCart.getId_shopping_cart());
            shoppingCartDTO.setProduct(sellProductMapper.showSellProductDTOSlist2(shoppingCart.getProductList()));
            shoppingCartDTO.setTotalPrice(shoppingCart.getTotalPrice());
            shoppingCartDTO.setTotalProducts(shoppingCart.getTotalProducts());
            shoppingCartDTOList.add(shoppingCartDTO);
        }
        return shoppingCartDTOList;
    }


}
