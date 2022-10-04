package com.ecommerce.mapper;

import com.ecommerce.dto.ItemShowDTO;
import com.ecommerce.dto.ShoppingCartDTO;
import com.ecommerce.model.Item;
import com.ecommerce.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingCartMapper {
    @Autowired
    private SellProductMapper sellProductMapper;


    public ShoppingCart dtoToShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId_shopping_cart(shoppingCartDTO.getId_shopping_cart());
        shoppingCart.setTotalProducts(shoppingCartDTO.getTotalProducts());
        shoppingCart.setTotalPrice(shoppingCartDTO.getTotalPrice());
        return shoppingCart;
    }

    public ShoppingCartDTO shoppingCartToDTO(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setId_shopping_cart(shoppingCart.getId_shopping_cart());
        shoppingCartDTO.setProduct(itemListToItemShowDTOList(shoppingCart.getProductList()));
        shoppingCartDTO.setTotalProducts(shoppingCart.getTotalProducts());
        shoppingCartDTO.setTotalPrice(shoppingCart.getTotalPrice());
        return shoppingCartDTO;
    }

    public List<ItemShowDTO> itemListToItemShowDTOList(List<Item> itemList) {
        List<ItemShowDTO> itemShowDTOList = new ArrayList<>();
        for (Item item : itemList) {
            ItemShowDTO itemShowDTO = new ItemShowDTO();
            itemShowDTO.setQuantity(item.getQuantity());
            itemShowDTO.setProducto(sellProductMapper.sellProductToDTO(item.getSellProduct()));
            itemShowDTOList.add(itemShowDTO);
        }
        return itemShowDTOList;
    }




}
