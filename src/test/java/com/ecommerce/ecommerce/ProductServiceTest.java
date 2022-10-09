package com.ecommerce.ecommerce;
import com.ecommerce.exception.Error;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Test
    public void getProductByNameTest() throws Error {
        Assertions.assertEquals("Malla", productService.findProductByName("Malla").getName());
    }

    @Test
    public void createProductTest() throws Error {
        Product product = new Product();
        product.setName("Malla");
        product.setBasePrice(500.50);
        product.setCustom_id(1L);
        product.setDescription("Malla de algodon");
        product.setManufacturingTime("10 dias");
        product.setName("Remera");
        Assertions.assertEquals(product, productService.createProduct(product));
    }

    @Test
    public void deleteProductTest() throws Error {
        Assertions.assertEquals("Product deleted", productService.deleteProduct(4L));
    }

    @Test
    public void setCustomizationTest() throws Error {
        Assertions.assertTrue(productService.setCustomization(1L, 1L));
    }

    @Test
    public void getProductByIdTest() throws Error {
        Assertions.assertEquals("Malla", productService.findProductById(1L).getName());
    }


}
