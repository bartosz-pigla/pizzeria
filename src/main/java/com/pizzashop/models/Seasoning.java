package com.pizzashop.models;

import com.pizzashop.models.interfaces.Nameable;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by barte on 09/12/2016.
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)

public class Seasoning  implements Serializable, Nameable {
    private Integer seasoningId;

    @NotNull
    @Length(max = 30)
    private String name;

    public Seasoning(String name) {
        this.name = name;
    }

    public Seasoning() {
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seasoningId")
    public Integer getSeasoningId() {
        return seasoningId;
    }

    public void setSeasoningId(Integer idSeasonings) {
        this.seasoningId = idSeasonings;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seasoning seasoning = (Seasoning) o;
        return Objects.equals(name, seasoning.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
