package com.ecommerce.service;

import com.ecommerce.model.dto.SellerProductAddDTO;
import com.ecommerce.model.dto.ShowSellProductDTO;
import com.ecommerce.model.dto.ShowSellerDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.mapper.SellProductMapper;
import com.ecommerce.mapper.SellerMapper;
import com.ecommerce.model.*;

import com.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerMapper sellerMapper;
    @Autowired
    private SellProductMapper sellProductMapper;
    @Autowired
    private SellerCustomizationRepository sellerCustomization;
    @Autowired
    private SellProductRepository sellProductRepository;
    @Autowired
    private UserRepository userRepository;

    public Seller findSellerById(Long id) throws Error {
        return sellerRepository.findById(id).orElseThrow(() -> new Error("Seller not found"));
    }

    public ShowSellerDTO findSellerByIdDTO(Long id) throws Error {
        return sellerMapper.toShowSellerDTO(sellerRepository.findById(id).orElseThrow(() -> new Error("Seller not found")));
    }

    public Seller saveSeller(Seller seller) throws Error {
        User user = userRepository.findById(seller.getUser_id()).orElseThrow(() -> new Error("User not found"));
        if (user.getRole_id().equals(1L)) {
            return sellerRepository.save(seller);
        }
        throw new Error("User is not a seller");
    }

    public List<ShowSellerDTO> findAll() {
        List<Seller> sellers = sellerRepository.findAll();
        List<ShowSellerDTO> showSellerDTOS = new ArrayList<>();
        for (Seller seller : sellers) {
            showSellerDTOS.add(sellerMapper.toShowSellerDTO(seller));
        }
        return showSellerDTOS;
    }

    public ShowSellProductDTO buyProductbySeller(SellerProductAddDTO buff, Long SellerID, Long idProduct) throws Error {
        Seller seller = findSellerById(SellerID);
        Product product = productRepository.findById(idProduct).orElseThrow(() -> new Error("Product not found"));
        SellerCustomization sellerCustom = new SellerCustomization(buff.getName() , buff.getPrice(), product.getBaseCustomizationOptions().getOption_custom() +"-" + buff.getDescription()  +"-"+ product.getBaseCustomizationOptions().getSector_custom());
        sellerCustomization.save(sellerCustom);
        SellProduct sellProduct = new SellProduct();
        sellProduct.setProduct(product);
        sellProduct.setSellingPrice(product.getBasePrice() + buff.getPrice());
        List<SellerCustomization> sellerCustomizations = new ArrayList<>();
        sellerCustomizations.add(sellerCustom);
        sellProduct.setSellerCustomizations(sellerCustomizations);
        seller.addSellProduct(sellProduct);
        sellProductRepository.save(sellProduct);
        return sellProductMapper.sellProductToDTO(sellProduct);
    }

    public Boolean deleteSeller(Long id) throws Error {
        System.out.println("Seller id: " + id);
        Seller seller = findSellerById(id);
        sellerRepository.delete(seller);
        return true;
    }

}
