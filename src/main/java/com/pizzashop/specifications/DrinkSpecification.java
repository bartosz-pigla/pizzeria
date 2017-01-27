package com.pizzashop.specifications;

import com.pizzashop.productFilters.DrinkFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Bartosz Pigla on 01/01/2017.
 */
public class DrinkSpecification<T> extends ProductSpecification<T> {
    public DrinkSpecification(DrinkFilter drinkFilter) {
        super(drinkFilter);
    }

    @Override
    public Predicate toPredicate(Root<T> drink, CriteriaQuery<?> query, CriteriaBuilder cb) {
        DrinkFilter drinkFilter=(DrinkFilter)productFilter;

        List<String> literCounts = drinkFilter.getLiterCounts();

        Predicate predicate=super.toPredicate(drink,query,cb);

        if(predicate==null)
            predicate=cb.conjunction();

        predicate = literCounts==null || literCounts.size()==0 ? predicate :cb.and(predicate, drink.get("literCount").in(literCounts));

        return predicate;
    }
}
