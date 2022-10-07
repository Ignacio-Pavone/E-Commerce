package com.ecommerce.service;
import com.ecommerce.model.dto.ProductFilterDTO;
import com.ecommerce.model.dto.ShowSellProductDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
@Component
public class ProductFiltersService {
    public Specification<ShowSellProductDTO> getByFilters(ProductFilterDTO sellProduct) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(sellProduct.getName())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + sellProduct.getName().toLowerCase() + "%"));
            }

            String orderByField = "name";
            query.orderBy(sellProduct.isASC() ? criteriaBuilder.asc(root.get(orderByField)) : criteriaBuilder.desc(root.get(orderByField)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
