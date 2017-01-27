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
import java.util.List;
import java.util.Set;

/**
 * Created by Bartosz Pigla on 31/12/2016.
 */
public class PizzaSpecification<T> extends ProductSpecification<T> {
    public PizzaSpecification(PizzaFilter pizzaFilter) {
        super(pizzaFilter);
    }

    @Override
    public Predicate toPredicate(Root<T> pizza, CriteriaQuery<?> query, CriteriaBuilder cb) {
        PizzaFilter pizzaFilter = (PizzaFilter) productFilter;

        Double minDoughPrice = pizzaFilter.getDoughMinPrice();
        Double maxDoughPrice = pizzaFilter.getDoughMaxPrice();

        List<DoughType> doughTypes=pizzaFilter.getDoughTypes();
        List<PizzaSize> pizzaSizes = pizzaFilter.getPizzaSizes();
        List<Ingredient> ingredients = pizzaFilter.getIngredients();

        Predicate predicate = super.toPredicate(pizza, query, cb);

        if (predicate == null)
            predicate = cb.conjunction();

        if (minDoughPrice != null && maxDoughPrice != null)
            predicate = minDoughPrice.equals(maxDoughPrice) ? predicate : cb.and(predicate, cb.between(pizza.<Double>get("doughPrice"), minDoughPrice, maxDoughPrice));

        //pizza.get("doughType").

        predicate = doughTypes == null || doughTypes.size()==0 ? predicate : cb.and(predicate, pizza.get("doughType").in(doughTypes));

        predicate = pizzaSizes == null || pizzaSizes.size()==0 ? predicate : cb.and(predicate, pizza.get("size").in(pizzaSizes));

        if(ingredients!=null){
            for (Ingredient ingredient:ingredients
                    ) {
                predicate = cb.and(predicate, cb.isMember(ingredient,pizza.get("ingredients")));
            }
        }

        return predicate;
    }
}
