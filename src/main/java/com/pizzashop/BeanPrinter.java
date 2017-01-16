package com.pizzashop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Created by barte on 09/12/2016.
 */
@Service
@Configurable
public class BeanPrinter {
    @Autowired
    ApplicationContext applicationContext;

   public BeanPrinter() {

    }

    @PostConstruct
    public void init() {
        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
    }
}
