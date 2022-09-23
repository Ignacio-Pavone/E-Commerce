package com.ecommerce.service;
import com.ecommerce.dto.ShowSellerDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.mapper.SellerMapper;
import com.ecommerce.model.Seller;
import com.ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private SellerMapper sellerMapper;

    public Seller findSellerById(Long id) throws Error {
        return sellerRepository.findById(id).orElseThrow(() -> new Error("Seller not found"));
    }
    public ShowSellerDTO findSellerByIdDTO(Long id) throws Error {
        return sellerMapper.toShowSellerDTO(sellerRepository.findById(id).orElseThrow(() -> new Error("Seller not found")));
    }
    public Seller saveSeller(Seller seller) throws Error {
        return sellerRepository.save(seller);
    }
    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }


}
