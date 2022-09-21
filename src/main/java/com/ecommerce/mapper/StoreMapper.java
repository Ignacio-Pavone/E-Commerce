package com.ecommerce.mapper;

import com.ecommerce.dto.StoreDTO;
import com.ecommerce.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {
        public StoreDTO storeToDTO(Store store) {
            StoreDTO storeDTO = new StoreDTO();
            storeDTO.setIdStore(store.getIdStore());
            storeDTO.setSeller_id(store.getUser().getSeller_id());
            storeDTO.setSellername(store.getUser().getUser().getName());
            storeDTO.setPublications(store.getPublications());
            storeDTO.setPaymentMethods(store.getPaymentMethods());
            return storeDTO;
        }

        public Store DTOtoStore(StoreDTO storeDTO) {
            Store store = new Store();
            store.setIdStore(storeDTO.getIdStore());
            store.setPublications(storeDTO.getPublications());
            store.setPaymentMethods(storeDTO.getPaymentMethods());
            return store;
        }

}
