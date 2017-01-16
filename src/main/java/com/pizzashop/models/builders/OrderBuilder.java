package com.pizzashop.models.builders;

import com.pizzashop.models.Client;
import com.pizzashop.models.Order;
import com.pizzashop.models.OrderPosition;
import com.pizzashop.models.enums.ProductOrderStatus;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OrderBuilder {
    private Date orderDate;
    private String address;
    private Date receiptDate;
    private BigDecimal price;
    private Client client;
    private ProductOrderStatus productOrderStatus;
    private Set<OrderPosition> orderPositions;

    public OrderBuilder setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public OrderBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public OrderBuilder setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public OrderBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public OrderBuilder setClient(Client client) {
        this.client = client;
        return this;
    }

    public OrderBuilder setProductOrderStatus(ProductOrderStatus productOrderStatus) {
        this.productOrderStatus = productOrderStatus;
        return this;
    }

    public OrderBuilder setOrderPositions(Set<OrderPosition> orderPositions){
        this.orderPositions=orderPositions;
        return this;
    }

    public OrderBuilder addOrderPositions(OrderPosition... orderPositions) {
        this.orderPositions = new HashSet<>(Arrays.asList(orderPositions));
        return this;
    }

    public Order createOrder() {
        return new Order(orderDate, address, receiptDate, price, client, productOrderStatus, orderPositions);
    }
}