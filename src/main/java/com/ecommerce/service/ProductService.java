package com.ecommerce.service;

import com.ecommerce.model.dto.ProductFilterDTO;
import com.ecommerce.model.dto.ShowSellProductDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.mapper.SellProductMapper;
import com.ecommerce.model.Product;
import com.ecommerce.model.SellProduct;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.SellProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellProductRepository sellProductRepository;
    @Autowired
    private SellProductMapper sellproductMapper;
    @Autowired
    private ProductFiltersService productFiltersService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) throws Error {
        return productRepository.findById(id).orElseThrow(() -> new Error("Product not found"));
    }

    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Product createProduct(Product product) throws Error {
        Product exist = findProductByName(product.getName());
        if (exist != null) {
            throw new Error("Product already exists");
        }
        return productRepository.save(product);
    }

    public String deleteProduct(Long id) throws Error {
        Product product = findProductById(id);
        productRepository.delete(product);
        return "Product deleted";
    }

    public boolean setCustomization(Long id_product, Long id_custom) throws Error {
        Product product = findProductById(id_product);
        if (product == null) {
            throw new Error("Product not found");
        }
        product.setCustom_id(id_custom);
        productRepository.save(product);
        return true;
    }

    public List<ShowSellProductDTO> findAllsellProducts() {
        List<SellProduct> productstoSell = sellProductRepository.findAll();
        List<ShowSellProductDTO> showSellsDTOS = new ArrayList<>();
        for (SellProduct sellProduct : productstoSell) {
            showSellsDTOS.add(sellproductMapper.sellProductToDTO(sellProduct));
        }
        return showSellsDTOS;
    }

    public List<ShowSellProductDTO> getByFilters(String title, String order) {
        ProductFilterDTO productFilterDTO = new ProductFilterDTO(title, order);
        List<Product> lista = productRepository.findAll(productFiltersService.getByFilters(productFilterDTO));
        return sellproductMapper.sellproductEntityList2DTOList(lista);
    }

}
