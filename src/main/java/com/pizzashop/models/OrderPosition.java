package com.pizzashop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pizzashop.annotations.Price;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by barte on 09/12/2016.
 */
@Entity
public class OrderPosition  implements Serializable {
    private Integer orderPositionId;

    @NotNull
    @Range(min = 0, max = 10)
    private Integer count;

    @NotNull
    @Price
    private BigDecimal price;

    @JsonIgnore
    @NotNull
    private Order order;
    @NotNull
    private Product product;
    @NotNull
    private Rebate rebate;

    public OrderPosition(Integer count, BigDecimal price, Order order, Product product, Rebate rebate) {
        this.count = count;
        this.price = price;
        this.order = order;
        this.product = product;
        this.rebate = rebate;
    }

    public OrderPosition() {
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderPositionId")
    public Integer getOrderPositionId() {
        return orderPositionId;
    }

    public void setOrderPositionId(Integer orderPositionId) {
        this.orderPositionId = orderPositionId;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductOrder_productOrderId", referencedColumnName = "productOrderId", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="productId")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="rebateId")
    public Rebate getRebate() {
        return rebate;
    }

    public void setRebate(Rebate rebate) {
        this.rebate = rebate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPosition that = (OrderPosition) o;
        return Objects.equals(count, that.count) &&
                Objects.equals(order, that.order) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, order, product);
    }
}
