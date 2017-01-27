package com.pizzashop.models.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bartosz Pigla on 30/12/2016.
 */
public enum DoughType {
    grube,
    cienkie;

    public static Set<String> names(){
        Set<String> names=new HashSet<>();
        for (DoughType doughType:values()
             ) {
            names.add(doughType.toString());
        }
        return names;
    }
}
