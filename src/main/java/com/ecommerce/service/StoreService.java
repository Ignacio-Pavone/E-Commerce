package com.ecommerce.service;

import com.ecommerce.dto.StoreDTO;
import com.ecommerce.exception.Error;
import com.ecommerce.mapper.StoreMapper;
import com.ecommerce.model.PaymentMethod;
import com.ecommerce.model.Seller;
import com.ecommerce.model.Store;
import com.ecommerce.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private SellerService sellerService;

    public List<StoreDTO> findall() {
        List<Store> store = storeRepository.findAll();
        List<StoreDTO> storeDTOS = new ArrayList<>();
        for (Store store1 : store) {
            storeDTOS.add(storeMapper.storeToDTO(store1));
        }
    return storeDTOS;
    }

    public StoreDTO findByName(String name) throws Error {
        return storeMapper.storeToDTO(storeRepository.findStoreByUser_User_Name(name));
    }

    public Store createStore (Long id) throws Error {
        Store store = new Store();
        Seller seller = sellerService.findSellerById(id);
        store.setUser(seller);
        for (StoreDTO stor1 : findall()) {
            if (stor1.getSeller_id().equals(seller.getSeller_id())) {
                throw new Error("Store already exists");
            }
        }
        return storeRepository.save(store);
    }


    public Store deleteStore (Long id) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        storeRepository.delete(store);
        return store;
    }

    public Store addPaymentMethod (Long id, PaymentMethod paymentMethod) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        for (PaymentMethod paymentMethod1 : store.getPaymentMethods()) {
            if (paymentMethod1.equals(paymentMethod)) {
                throw new Error("Payment method already exists");
            }
        }
        store.getPaymentMethods().add(paymentMethod);
        return storeRepository.save(store);
    }

    public Store removePaymentMethod (Long id, PaymentMethod paymentMethod) throws Error {
        Store store = storeRepository.findById(id).orElseThrow(() -> new Error("Store not found"));
        for (PaymentMethod paymentMethod1 : store.getPaymentMethods()) {
            if (paymentMethod1.equals(paymentMethod)) {
                store.getPaymentMethods().remove(paymentMethod);
                return storeRepository.save(store);
            }
        }
        throw new Error("Payment method not found");
    }
}
