package com;

import org.junit.Assert;

import java.util.Collection;

/**
 * Created by barte on 31/12/2016.
 */
public class TestHelper {
    public static <T> void compareNotOrderedCollections(Collection<T> first, Collection<T> second){
        Assert.assertTrue(first.containsAll(second)
                && second.containsAll(first));
    }
}
