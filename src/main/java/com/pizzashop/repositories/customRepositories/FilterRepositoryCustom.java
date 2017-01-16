package com.pizzashop.repositories.customRepositories;

import com.pizzashop.models.interfaces.Nameable;

import java.util.List;

/**
 * Created by barte on 31/12/2016.
 */
public interface FilterRepositoryCustom {
    <T extends Nameable> List<String> getNames(Class<T> tClass);

    List<String> getLiterCounts();
}

