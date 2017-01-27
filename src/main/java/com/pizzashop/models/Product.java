package com.pizzashop.models;

import com.pizzashop.annotations.ImageUrl;
import com.pizzashop.annotations.Price;
import com.pizzashop.models.interfaces.Nameable;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bartosz Pigla on 09/12/2016.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public abstract class Product implements Serializable, Nameable {
    private Integer productId;
    @NotNull
    @Length(max = 30)
    private String name;
    @NotNull
    @Length(max = 255)
    private String description;
    @NotNull
    @Price
    private Double price;
    @NotEmpty
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    private Set<Rebate> rebates = new HashSet<>();

    @NotNull
    @ImageUrl
    private String imageUrl;

    @Transient
    protected String type;

    private boolean archival;

    public Product() {
    }

    public Product(String name, String description, Double price, Set<Rebate> rebates, String url, boolean archival) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rebates = rebates;
        this.imageUrl = url;
        this.archival=archival;
    }

    //    public Product(
//            String name,
//            String description,
//            BigDecimal price,
//            Set<Rebate> rebates
//    ) {
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.rebates = rebates;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productId", updatable = false, nullable = false)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ProductRebate",
            joinColumns = @JoinColumn(name = "productId", referencedColumnName = "productId"),
            inverseJoinColumns = @JoinColumn(name = "rebateId", referencedColumnName = "rebateId")
    )
    public Set<Rebate> getRebates() {
        return rebates;
    }

    public void setRebates(Set<Rebate> rebates) {
        this.rebates = rebates;
    }

    public void addRebate(Rebate rebate) {
        rebates.add(rebate);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String url) {
        this.imageUrl = url;
    }

    @Basic
    @Column(name = "archival")
    public boolean getArchival() {
        return archival;
    }

    public void setArchival(boolean archival) {
        this.archival = archival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        return imageUrl != null ? imageUrl.equals(product.imageUrl) : product.imageUrl == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return Objects.equals(name, product.name) &&
//                Objects.equals(price, product.price);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, price);
//    }
}
