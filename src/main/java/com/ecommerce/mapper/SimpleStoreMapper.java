package com.ecommerce.mapper;
import com.ecommerce.dto.SimpleStoreDTO;
import com.ecommerce.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleStoreMapper {
        @Autowired
        private SimplePublicationMapper simplePublicationMapper;
        public SimpleStoreDTO storeToSimpleStore(Store store) {
            SimpleStoreDTO simpleStoreDTO = new SimpleStoreDTO();
            simpleStoreDTO.setSellername(store.getUser().getUser().getName());
            simpleStoreDTO.setPublications(simplePublicationMapper.listpublicationToDTo(store.getPublications()));
            simpleStoreDTO.setPaymentMethods(store.getPaymentMethods());
            return simpleStoreDTO;
        }


}
