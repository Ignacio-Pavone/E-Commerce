package com.ecommerce.mapper;

import com.ecommerce.model.dto.StoreDTO;
import com.ecommerce.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    @Autowired
    private PublicationMapper publicationMapper;
        public StoreDTO storeToDTO(Store store) {
            StoreDTO storeDTO = new StoreDTO();
            storeDTO.setIdStore(store.getIdStore());
            storeDTO.setSeller_id(store.getUser().getSeller_id());
            storeDTO.setSellername(store.getUser().getUser().getName());
            if (store.getPublications() != null) {
                storeDTO.setPublications(publicationMapper.toPublicationDTO(store.getPublications()));
            }
            storeDTO.setPaymentMethods(store.getPaymentMethods());
            return storeDTO;
        }




}
