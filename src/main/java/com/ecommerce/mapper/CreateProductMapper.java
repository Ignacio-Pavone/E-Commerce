package com.ecommerce.mapper;

import com.ecommerce.model.Product;
import com.ecommerce.model.dto.CreateProductDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateProductMapper {

    public Product toProduct(CreateProductDTO createProductDTO) {
        Product product = new Product();
        product.setName(createProductDTO.getName());
        product.setDescription(createProductDTO.getDescription());
        product.setBasePrice(createProductDTO.getBasePrice());
        product.setManufacturingTime(createProductDTO.getManufacturingTime());
        return product;
    }

    public CreateProductDTO toCreateProductDTO(Product product) {
        CreateProductDTO createProductDTO = new CreateProductDTO();
        createProductDTO.setName(product.getName());
        createProductDTO.setDescription(product.getDescription());
        createProductDTO.setBasePrice(product.getBasePrice());
        createProductDTO.setManufacturingTime(product.getManufacturingTime());
        return createProductDTO;
    }
}
