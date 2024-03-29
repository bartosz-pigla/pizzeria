package com.pizzashop.specifications;

import com.pizzashop.models.Rebate;
import com.pizzashop.productFilters.ProductFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Bartosz Pigla on 31/12/2016.
 */
public class ProductSpecification<T> implements Specification<T> {
    protected ProductFilter productFilter;

    public ProductSpecification(ProductFilter productFilter) {
        this.productFilter = productFilter;
    }

    @Override
    public Predicate toPredicate(Root<T> product, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Double minPrice = productFilter.getMinPrice();
        Double maxPrice = productFilter.getMaxPrice();

        List<String> names = productFilter.getNames();
        List<Rebate> rebates = productFilter.getRebates();

        Predicate predicate=cb.conjunction();

        predicate =cb.and(predicate, cb.isFalse(product.<Boolean>get("archival")));

        if (minPrice != null && maxPrice != null)
            predicate = maxPrice.equals(minPrice) ? predicate : cb.and(predicate, cb.between(product.<Double>get("price"), minPrice, maxPrice));

        predicate = names == null || names.size()==0 ? predicate : cb.and(predicate, product.get("name").in(names));

        if(rebates!=null){
            for (Rebate rebate:rebates
                    ) {
                predicate = cb.and(predicate, cb.isMember(rebate,product.get("rebates")));
            }
        }

        return predicate;
    }
}
