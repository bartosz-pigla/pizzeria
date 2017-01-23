package com.pizzashop.specifications;

import com.pizzashop.models.Ingredient;
import com.pizzashop.models.enums.DoughType;
import com.pizzashop.models.enums.PizzaSize;
import com.pizzashop.productFilters.PizzaFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by barte on 31/12/2016.
 */
public class PizzaSpecification<T> extends ProductSpecification<T> {
    public PizzaSpecification(PizzaFilter pizzaFilter) {
        super(pizzaFilter);
    }

    @Override
    public Predicate toPredicate(Root<T> pizza, CriteriaQuery<?> query, CriteriaBuilder cb) {
        PizzaFilter pizzaFilter = (PizzaFilter) productFilter;

        BigDecimal minDoughPrice = pizzaFilter.getDoughMinPrice();
        BigDecimal maxDoughPrice = pizzaFilter.getDoughMaxPrice();

        Set<DoughType> doughTypes = new HashSet<>(pizzaFilter.getDoughTypes());
        Set<PizzaSize> pizzaSizes = new HashSet<>(pizzaFilter.getPizzaSizes());
        Set<Ingredient> ingredients = new HashSet<>(pizzaFilter.getIngredients());

        Predicate predicate = super.toPredicate(pizza, query, cb);

        if (predicate == null)
            predicate = cb.conjunction();

        if (minDoughPrice != null && maxDoughPrice != null)
            predicate = minDoughPrice.equals(maxDoughPrice) ? predicate : cb.and(predicate, cb.between(pizza.<BigDecimal>get("doughPrice"), minDoughPrice, maxDoughPrice));

        predicate = doughTypes == null || doughTypes.size()==0 ? predicate : cb.and(predicate, pizza.get("doughType").in(doughTypes));

        predicate = pizzaSizes == null || pizzaSizes.size()==0 ? predicate : cb.and(predicate, pizza.get("size").in(pizzaSizes));


        for (Ingredient ingredient:ingredients
             ) {
            predicate = cb.and(predicate, cb.isMember(ingredient,pizza.get("ingredients")));
        }
        return predicate;
    }
}
