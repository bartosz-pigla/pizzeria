package com.pizzashop.models;

import com.pizzashop.annotations.Price;
import com.pizzashop.models.enums.ProductOrderStatus;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

/**
 * Created by barte on 09/12/2016.
 */
@Entity(name = "ProductOrder")
public class Order  implements Serializable {
    private Integer productOrderId;

    @DateTimeFormat(pattern = "dd-mm-yyyy hh:mm:ss")
    @NotNull
    private Date orderDate,receiptDate;

    @NotNull
    @Length(max=255)
    private String address;

    @NotNull
    @Price
    private BigDecimal price;
    @NotNull
    private Client client;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductOrderStatus productOrderStatus;

    @NotEmpty
    private Set<OrderPosition> orderPositions=new HashSet<>();

    public Order(Date orderDate, String address, Date receiptDate, BigDecimal price, Client client, ProductOrderStatus productOrderStatus, Set<OrderPosition> orderPositions) {
        this.orderDate = orderDate;
        this.address = address;
        this.receiptDate = receiptDate;
        this.price = price;
        this.client = client;
        this.productOrderStatus = productOrderStatus;
        this.orderPositions = orderPositions;
    }

    public Order() {
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productOrderId")
    public Integer getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Integer productOrderId) {
        this.productOrderId = productOrderId;
    }

    @Basic
    @Column(name = "orderDate")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "receiptDate")
    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    public Set<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Set<OrderPosition> orderPositionsList) {
        this.orderPositions = orderPositionsList;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Client_clientId", referencedColumnName = "clientId", nullable = false)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addOrderPosition(OrderPosition position){
        orderPositions.add(position);
    }

    @Basic
    @Column(name = "productOrderStatus")
    public ProductOrderStatus getProductOrderStatus() {
        return productOrderStatus;
    }

    public void setProductOrderStatus(ProductOrderStatus productOrderStatus) {
        this.productOrderStatus = productOrderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(client, order.client) &&
                Objects.equals(orderPositions, order.orderPositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, orderPositions);
    }
}
