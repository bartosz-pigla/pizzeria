package com.pizzashop.productFilters;

import com.pizzashop.models.Rebate;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Bartosz Pigla on 31/12/2016.
 */
public class DrinkFilter extends ProductFilter {
    protected List<String> literCounts;

    public DrinkFilter(List<Rebate> rebates, List<String> names, Double minPrice, Double maxPrice, List<String> literCounts) {
        super(rebates, names, minPrice, maxPrice);
        this.literCounts = literCounts;
    }

    public DrinkFilter(){

    }

    public List<String> getLiterCounts() {
        return literCounts;
    }

    public void setLiterCounts(List<String> literCounts) {
        this.literCounts = literCounts;
    }


}
