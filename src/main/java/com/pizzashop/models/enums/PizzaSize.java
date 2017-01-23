package com.pizzashop.models.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bartek on 1/23/17.
 */
public enum PizzaSize {
    mala,
    duża;

    public static Set<String> names(){
        Set<String> names=new HashSet<>();
        for (PizzaSize pizzaSize:values()
                ) {
            names.add(pizzaSize.toString());
        }
        return names;
    }
}
