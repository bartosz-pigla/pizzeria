package com.pizzashop.productFilters;

import com.pizzashop.models.Rebate;
import com.pizzashop.models.Seasoning;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by barte on 31/12/2016.
 */
public class SauceFilter extends ProductFilter {
    protected List<Seasoning> seasonings;

    public SauceFilter(List<Rebate> rebates, List<String> names, Double minPrice, Double maxPrice, List<Seasoning> seasonings) {
        super(rebates, names, minPrice, maxPrice);
        this.seasonings = seasonings;
    }

    public SauceFilter(){

    }

    public List<Seasoning> getSeasonings() {
        return seasonings;
    }

    public void setSeasonings(List<Seasoning> seasonings) {
        this.seasonings = seasonings;
    }
}
