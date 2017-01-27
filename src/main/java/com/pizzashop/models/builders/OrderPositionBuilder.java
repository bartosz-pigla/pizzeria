package com.pizzashop.models.builders;

import com.pizzashop.models.Order;
import com.pizzashop.models.OrderPosition;
import com.pizzashop.models.Product;
import com.pizzashop.models.Rebate;

import java.math.BigDecimal;

public class OrderPositionBuilder {
    private Integer count;
    private Double price;
    private Order order;
    private Product product;
    private Rebate rebate;

    public OrderPositionBuilder setCount(Integer count) {
        this.count = count;
        return this;
    }

    public OrderPositionBuilder setPrice(Double price) {
        this.price = price;
        return this;
    }

    public OrderPositionBuilder setOrder(Order order) {
        this.order = order;
        return this;
    }

    public OrderPositionBuilder setProduct(Product product) {
        this.product = product;
        return this;
    }

    public OrderPositionBuilder setRebate(Rebate rebate) {
        this.rebate = rebate;
        return this;
    }

    public OrderPosition createOrderPosition() {
        return new OrderPosition(count, price, order, product, rebate);
    }
}